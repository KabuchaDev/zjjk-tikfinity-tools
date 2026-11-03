const kinds = new Set(['basic', 'elite', 'boss']);
const sides = new Set(['red', 'blue']);

export class EventRouter {
  constructor(config, now = () => Date.now()) {
    this.config = config; this.now = now;
    this.seen = new Map(); this.cooldowns = new Map();
  }
  route(envelope) {
    const event = envelope?.event, data = envelope?.data;
    if (!data || !['chat', 'gift'].includes(event)) return null;
    const now = this.now();
    for (const [key, time] of this.seen) if (now - time > 120000) this.seen.delete(key);
    for (const [key, time] of this.cooldowns) if (now - time > 120000) this.cooldowns.delete(key);
    // Bound caches even on very busy streams.
    if (this.seen.size > 10000) this.seen.delete(this.seen.keys().next().value);
    if (this.cooldowns.size > 10000) this.cooldowns.delete(this.cooldowns.keys().next().value);
    let side, kind = 'basic', count = 1;
    if (event === 'chat') {
      const comment = String(data.comment ?? '').normalize('NFKC').trim().toLowerCase();
      side = comment === 'ronaldo' ? 'red' : comment === 'messi' ? 'blue' : null;
      if (!side) return null;
    } else {
      const mapping = this.config.giftMappings?.[String(data.giftId)];
      if (!mapping || !sides.has(mapping.side) || !kinds.has(mapping.kind)) return null;
      // A streak emits multiple cumulative updates. Consume only its final event.
      if (Number(data.giftType) === 1 && data.repeatEnd !== true) return null;
      side = mapping.side; kind = mapping.kind;
      const repeats = Math.max(1, Math.min(15, Math.floor(Number(data.repeatCount) || 1)));
      const amount = Math.max(1, Math.min(15, Math.floor(Number(mapping.count) || 1)));
      count = Math.min(15, repeats * amount);
    }
    const identity = String(data.userId ?? data.uniqueId ?? data.user?.userId ?? data.user?.uniqueId ?? '');
    // An identity is necessary for per-viewer cooldowns. Missing IDs are dropped.
    if (!identity) return null;
    const messageId = data.msgId ?? data.messageId;
    const dedupKey = messageId == null ? null : `${event}:${String(messageId)}`;
    if (dedupKey && this.seen.has(dedupKey)) return null;
    const last = this.cooldowns.get(identity);
    if (event === 'chat' && last !== undefined && now-last < this.config.commentCooldownSeconds*1000) return null;
    if (dedupKey) this.seen.set(dedupKey, now);
    if (event === 'chat') this.cooldowns.set(identity, now);
    const viewer = String(data.uniqueId ?? data.nickname ?? data.user?.uniqueId ?? identity)
      .replace(/[^a-zA-Z0-9_.-]/g, '_').slice(0,24) || 'Viewer';
    return { side, kind, count, viewer,
      command: `jjkarena spawn ${side} ${kind} ${count} ${viewer}` };
  }
}
