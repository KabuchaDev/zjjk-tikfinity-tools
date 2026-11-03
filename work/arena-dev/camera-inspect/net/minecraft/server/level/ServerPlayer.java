/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.net.InetAddresses
 *  com.mojang.authlib.GameProfile
 *  com.mojang.datafixers.util.Either
 *  com.mojang.logging.LogUtils
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  javax.annotation.Nullable
 *  net.minecraft.BlockUtil$FoundRectangle
 *  net.minecraft.ChatFormatting
 *  net.minecraft.CrashReport
 *  net.minecraft.CrashReportCategory
 *  net.minecraft.ReportedException
 *  net.minecraft.Util
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.arguments.EntityAnchorArgument$Anchor
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Axis
 *  net.minecraft.core.GlobalPos
 *  net.minecraft.core.Position
 *  net.minecraft.core.SectionPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtOps
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.PacketSendListener
 *  net.minecraft.network.chat.ChatType$Bound
 *  net.minecraft.network.chat.CommonComponents
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.HoverEvent
 *  net.minecraft.network.chat.HoverEvent$Action
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.chat.OutgoingChatMessage
 *  net.minecraft.network.chat.RemoteChatSession
 *  net.minecraft.network.chat.Style
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientGamePacketListener
 *  net.minecraft.network.protocol.game.ClientboundAddPlayerPacket
 *  net.minecraft.network.protocol.game.ClientboundAnimatePacket
 *  net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
 *  net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket
 *  net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket
 *  net.minecraft.network.protocol.game.ClientboundContainerClosePacket
 *  net.minecraft.network.protocol.game.ClientboundEntityEventPacket
 *  net.minecraft.network.protocol.game.ClientboundForgetLevelChunkPacket
 *  net.minecraft.network.protocol.game.ClientboundGameEventPacket
 *  net.minecraft.network.protocol.game.ClientboundHorseScreenOpenPacket
 *  net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket
 *  net.minecraft.network.protocol.game.ClientboundLevelEventPacket
 *  net.minecraft.network.protocol.game.ClientboundMerchantOffersPacket
 *  net.minecraft.network.protocol.game.ClientboundOpenBookPacket
 *  net.minecraft.network.protocol.game.ClientboundOpenScreenPacket
 *  net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerCombatEndPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerCombatEnterPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
 *  net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket$Action
 *  net.minecraft.network.protocol.game.ClientboundPlayerLookAtPacket
 *  net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket
 *  net.minecraft.network.protocol.game.ClientboundResourcePackPacket
 *  net.minecraft.network.protocol.game.ClientboundRespawnPacket
 *  net.minecraft.network.protocol.game.ClientboundServerDataPacket
 *  net.minecraft.network.protocol.game.ClientboundSetCameraPacket
 *  net.minecraft.network.protocol.game.ClientboundSetExperiencePacket
 *  net.minecraft.network.protocol.game.ClientboundSetHealthPacket
 *  net.minecraft.network.protocol.game.ClientboundSoundPacket
 *  net.minecraft.network.protocol.game.ClientboundSystemChatPacket
 *  net.minecraft.network.protocol.game.ClientboundTabListPacket
 *  net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket
 *  net.minecraft.network.protocol.game.ServerboundClientInformationPacket
 *  net.minecraft.network.protocol.status.ServerStatus
 *  net.minecraft.network.protocol.status.ServerStatus$Favicon
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.PlayerAdvancements
 *  net.minecraft.server.level.PlayerRespawnLogic
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayerGameMode
 *  net.minecraft.server.level.TicketType
 *  net.minecraft.server.network.ServerGamePacketListenerImpl
 *  net.minecraft.server.network.TextFilter
 *  net.minecraft.server.players.PlayerList
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.stats.RecipeBook
 *  net.minecraft.stats.ServerRecipeBook
 *  net.minecraft.stats.ServerStatsCounter
 *  net.minecraft.stats.Stat
 *  net.minecraft.stats.Stats
 *  net.minecraft.tags.DamageTypeTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.util.Unit
 *  net.minecraft.world.Container
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Entity$RemovalReason
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.NeutralMob
 *  net.minecraft.world.entity.RelativeMovement
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.monster.Monster
 *  net.minecraft.world.entity.monster.warden.WardenSpawnTracker
 *  net.minecraft.world.entity.player.ChatVisiblity
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.player.Player$BedSleepingProblem
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerListener
 *  net.minecraft.world.inventory.ContainerSynchronizer
 *  net.minecraft.world.inventory.HorseInventoryMenu
 *  net.minecraft.world.item.ComplexItem
 *  net.minecraft.world.item.ItemCooldowns
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.ServerItemCooldowns
 *  net.minecraft.world.item.WrittenBookItem
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.trading.MerchantOffers
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.GameType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.biome.BiomeManager
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.HorizontalDirectionalBlock
 *  net.minecraft.world.level.block.NetherPortalBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.CommandBlockEntity
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.border.WorldBorder
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.portal.PortalInfo
 *  net.minecraft.world.level.storage.LevelData
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.scores.PlayerTeam
 *  net.minecraft.world.scores.Score
 *  net.minecraft.world.scores.Team
 *  net.minecraft.world.scores.Team$Visibility
 *  net.minecraft.world.scores.criteria.ObjectiveCriteria
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ITeleporter
 *  net.minecraftforge.entity.PartEntity
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.player.PlayerContainerEvent$Close
 *  net.minecraftforge.event.entity.player.PlayerContainerEvent$Open
 *  net.minecraftforge.eventbus.api.Event
 *  org.slf4j.Logger
 */
package net.minecraft.server.level;

import com.google.common.collect.Lists;
import com.google.common.net.InetAddresses;
import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.BlockUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.Util;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Position;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.PacketSendListener;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.RemoteChatSession;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket;
import net.minecraft.network.protocol.game.ClientboundContainerClosePacket;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ClientboundForgetLevelChunkPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundHorseScreenOpenPacket;
import net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundMerchantOffersPacket;
import net.minecraft.network.protocol.game.ClientboundOpenBookPacket;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerCombatEndPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerCombatEnterPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerLookAtPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundResourcePackPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.network.protocol.game.ClientboundServerDataPacket;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ServerboundClientInformationPacket;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.PlayerRespawnLogic;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.server.level.TicketType;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.network.TextFilter;
import net.minecraft.server.players.PlayerList;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.ServerRecipeBook;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.WardenSpawnTracker;
import net.minecraft.world.entity.player.ChatVisiblity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.inventory.ContainerSynchronizer;
import net.minecraft.world.inventory.HorseInventoryMenu;
import net.minecraft.world.item.ComplexItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ServerItemCooldowns;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Team;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.Event;
import org.slf4j.Logger;

public class ServerPlayer
extends Player {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int NEUTRAL_MOB_DEATH_NOTIFICATION_RADII_XZ = 32;
    private static final int NEUTRAL_MOB_DEATH_NOTIFICATION_RADII_Y = 10;
    public ServerGamePacketListenerImpl connection;
    public final MinecraftServer server;
    public final ServerPlayerGameMode gameMode;
    private final PlayerAdvancements advancements;
    private final ServerStatsCounter stats;
    private float lastRecordedHealthAndAbsorption = Float.MIN_VALUE;
    private int lastRecordedFoodLevel = Integer.MIN_VALUE;
    private int lastRecordedAirLevel = Integer.MIN_VALUE;
    private int lastRecordedArmor = Integer.MIN_VALUE;
    private int lastRecordedLevel = Integer.MIN_VALUE;
    private int lastRecordedExperience = Integer.MIN_VALUE;
    private float lastSentHealth = -1.0E8f;
    private int lastSentFood = -99999999;
    private boolean lastFoodSaturationZero = true;
    private int lastSentExp = -99999999;
    private int spawnInvulnerableTime = 60;
    private ChatVisiblity chatVisibility = ChatVisiblity.FULL;
    private boolean canChatColor = true;
    private long lastActionTime = Util.getMillis();
    @Nullable
    private Entity camera;
    private boolean isChangingDimension;
    private boolean seenCredits;
    private final ServerRecipeBook recipeBook = new ServerRecipeBook();
    @Nullable
    private Vec3 levitationStartPos;
    private int levitationStartTime;
    private boolean disconnected;
    @Nullable
    private Vec3 startingToFallPosition;
    @Nullable
    private Vec3 enteredNetherPosition;
    @Nullable
    private Vec3 enteredLavaOnVehiclePosition;
    private SectionPos lastSectionPos = SectionPos.of((int)0, (int)0, (int)0);
    private ResourceKey<Level> respawnDimension = Level.OVERWORLD;
    @Nullable
    private BlockPos respawnPosition;
    private boolean respawnForced;
    private float respawnAngle;
    private final TextFilter textFilter;
    private boolean textFilteringEnabled;
    private boolean allowsListing;
    private WardenSpawnTracker wardenSpawnTracker = new WardenSpawnTracker(0, 0, 0);
    private final ContainerSynchronizer containerSynchronizer = new /* Unavailable Anonymous Inner Class!! */;
    private final ContainerListener containerListener = new /* Unavailable Anonymous Inner Class!! */;
    @Nullable
    private RemoteChatSession chatSession;
    public int containerCounter;
    public int latency;
    public boolean wonGame;
    private String language = "en_us";
    private Component tabListHeader = Component.empty();
    private Component tabListFooter = Component.empty();
    private boolean hasTabListName = false;
    private Component tabListDisplayName = null;

    public ServerPlayer(MinecraftServer p_254143_, ServerLevel p_254435_, GameProfile p_253651_) {
        super((Level)p_254435_, p_254435_.getSharedSpawnPos(), p_254435_.getSharedSpawnAngle(), p_253651_);
        this.textFilter = p_254143_.createTextFilterForPlayer(this);
        this.gameMode = p_254143_.createGameModeForPlayer(this);
        this.server = p_254143_;
        this.stats = p_254143_.getPlayerList().getPlayerStats((Player)this);
        this.advancements = p_254143_.getPlayerList().getPlayerAdvancements(this);
        this.setMaxUpStep(1.0f);
        this.fudgeSpawnLocation(p_254435_);
    }

    private void fudgeSpawnLocation(ServerLevel p_9202_) {
        BlockPos blockpos = p_9202_.getSharedSpawnPos();
        if (p_9202_.dimensionType().hasSkyLight() && p_9202_.getServer().getWorldData().getGameType() != GameType.ADVENTURE) {
            long k;
            long l;
            int i = Math.max(0, this.server.getSpawnRadius(p_9202_));
            int j = Mth.floor((double)p_9202_.getWorldBorder().getDistanceToBorder((double)blockpos.getX(), (double)blockpos.getZ()));
            if (j < i) {
                i = j;
            }
            if (j <= 1) {
                i = 1;
            }
            int i1 = (l = (k = (long)(i * 2 + 1)) * k) > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)l;
            int j1 = this.getCoprime(i1);
            int k1 = RandomSource.create().nextInt(i1);
            for (int l1 = 0; l1 < i1; ++l1) {
                int i2 = (k1 + j1 * l1) % i1;
                int j2 = i2 % (i * 2 + 1);
                int k2 = i2 / (i * 2 + 1);
                BlockPos blockpos1 = PlayerRespawnLogic.getOverworldRespawnPos((ServerLevel)p_9202_, (int)(blockpos.getX() + j2 - i), (int)(blockpos.getZ() + k2 - i));
                if (blockpos1 == null) continue;
                this.moveTo(blockpos1, 0.0f, 0.0f);
                if (!p_9202_.noCollision((Entity)this)) {
                    continue;
                }
                break;
            }
        } else {
            this.moveTo(blockpos, 0.0f, 0.0f);
            while (!p_9202_.noCollision((Entity)this) && this.getY() < (double)(p_9202_.getMaxBuildHeight() - 1)) {
                this.setPos(this.getX(), this.getY() + 1.0, this.getZ());
            }
        }
    }

    private int getCoprime(int p_9238_) {
        return p_9238_ <= 16 ? p_9238_ - 1 : 17;
    }

    public void readAdditionalSaveData(CompoundTag p_9131_) {
        super.readAdditionalSaveData(p_9131_);
        if (p_9131_.contains("warden_spawn_tracker", 10)) {
            WardenSpawnTracker.CODEC.parse(new Dynamic((DynamicOps)NbtOps.INSTANCE, (Object)p_9131_.get("warden_spawn_tracker"))).resultOrPartial(arg_0 -> ((Logger)LOGGER).error(arg_0)).ifPresent(p_248205_ -> {
                this.wardenSpawnTracker = p_248205_;
            });
        }
        if (p_9131_.contains("enteredNetherPosition", 10)) {
            CompoundTag compoundtag = p_9131_.getCompound("enteredNetherPosition");
            this.enteredNetherPosition = new Vec3(compoundtag.getDouble("x"), compoundtag.getDouble("y"), compoundtag.getDouble("z"));
        }
        this.seenCredits = p_9131_.getBoolean("seenCredits");
        if (p_9131_.contains("recipeBook", 10)) {
            this.recipeBook.fromNbt(p_9131_.getCompound("recipeBook"), this.server.getRecipeManager());
        }
        if (this.isSleeping()) {
            this.stopSleeping();
        }
        if (p_9131_.contains("SpawnX", 99) && p_9131_.contains("SpawnY", 99) && p_9131_.contains("SpawnZ", 99)) {
            this.respawnPosition = new BlockPos(p_9131_.getInt("SpawnX"), p_9131_.getInt("SpawnY"), p_9131_.getInt("SpawnZ"));
            this.respawnForced = p_9131_.getBoolean("SpawnForced");
            this.respawnAngle = p_9131_.getFloat("SpawnAngle");
            if (p_9131_.contains("SpawnDimension")) {
                this.respawnDimension = Level.RESOURCE_KEY_CODEC.parse((DynamicOps)NbtOps.INSTANCE, (Object)p_9131_.get("SpawnDimension")).resultOrPartial(arg_0 -> ((Logger)LOGGER).error(arg_0)).orElse(Level.OVERWORLD);
            }
        }
    }

    public void addAdditionalSaveData(CompoundTag p_9197_) {
        super.addAdditionalSaveData(p_9197_);
        WardenSpawnTracker.CODEC.encodeStart((DynamicOps)NbtOps.INSTANCE, (Object)this.wardenSpawnTracker).resultOrPartial(arg_0 -> ((Logger)LOGGER).error(arg_0)).ifPresent(p_9134_ -> p_9197_.put("warden_spawn_tracker", p_9134_));
        this.storeGameTypes(p_9197_);
        p_9197_.putBoolean("seenCredits", this.seenCredits);
        if (this.enteredNetherPosition != null) {
            CompoundTag compoundtag = new CompoundTag();
            compoundtag.putDouble("x", this.enteredNetherPosition.x);
            compoundtag.putDouble("y", this.enteredNetherPosition.y);
            compoundtag.putDouble("z", this.enteredNetherPosition.z);
            p_9197_.put("enteredNetherPosition", (Tag)compoundtag);
        }
        Entity entity1 = this.getRootVehicle();
        Entity entity = this.getVehicle();
        if (entity != null && entity1 != this && entity1.hasExactlyOnePlayerPassenger()) {
            CompoundTag compoundtag1 = new CompoundTag();
            CompoundTag compoundtag2 = new CompoundTag();
            entity1.save(compoundtag2);
            compoundtag1.putUUID("Attach", entity.getUUID());
            compoundtag1.put("Entity", (Tag)compoundtag2);
            p_9197_.put("RootVehicle", (Tag)compoundtag1);
        }
        p_9197_.put("recipeBook", (Tag)this.recipeBook.toNbt());
        p_9197_.putString("Dimension", this.level().dimension().location().toString());
        if (this.respawnPosition != null) {
            p_9197_.putInt("SpawnX", this.respawnPosition.getX());
            p_9197_.putInt("SpawnY", this.respawnPosition.getY());
            p_9197_.putInt("SpawnZ", this.respawnPosition.getZ());
            p_9197_.putBoolean("SpawnForced", this.respawnForced);
            p_9197_.putFloat("SpawnAngle", this.respawnAngle);
            ResourceLocation.CODEC.encodeStart((DynamicOps)NbtOps.INSTANCE, (Object)this.respawnDimension.location()).resultOrPartial(arg_0 -> ((Logger)LOGGER).error(arg_0)).ifPresent(p_248207_ -> p_9197_.put("SpawnDimension", p_248207_));
        }
    }

    public void setExperiencePoints(int p_8986_) {
        float f = this.getXpNeededForNextLevel();
        float f1 = (f - 1.0f) / f;
        this.experienceProgress = Mth.clamp((float)((float)p_8986_ / f), (float)0.0f, (float)f1);
        this.lastSentExp = -1;
    }

    public void setExperienceLevels(int p_9175_) {
        this.experienceLevel = p_9175_;
        this.lastSentExp = -1;
    }

    public void giveExperienceLevels(int p_9200_) {
        super.giveExperienceLevels(p_9200_);
        this.lastSentExp = -1;
    }

    public void onEnchantmentPerformed(ItemStack p_9079_, int p_9080_) {
        super.onEnchantmentPerformed(p_9079_, p_9080_);
        this.lastSentExp = -1;
    }

    public void initMenu(AbstractContainerMenu p_143400_) {
        p_143400_.addSlotListener(this.containerListener);
        p_143400_.setSynchronizer(this.containerSynchronizer);
    }

    public void initInventoryMenu() {
        this.initMenu((AbstractContainerMenu)this.inventoryMenu);
    }

    public void onEnterCombat() {
        super.onEnterCombat();
        this.connection.send((Packet)new ClientboundPlayerCombatEnterPacket());
    }

    public void onLeaveCombat() {
        super.onLeaveCombat();
        this.connection.send((Packet)new ClientboundPlayerCombatEndPacket(this.getCombatTracker()));
    }

    protected void onInsideBlock(BlockState p_9103_) {
        CriteriaTriggers.ENTER_BLOCK.trigger(this, p_9103_);
    }

    protected ItemCooldowns createItemCooldowns() {
        return new ServerItemCooldowns(this);
    }

    public void tick() {
        Entity entity;
        this.gameMode.tick();
        this.wardenSpawnTracker.tick();
        --this.spawnInvulnerableTime;
        if (this.invulnerableTime > 0) {
            --this.invulnerableTime;
        }
        this.containerMenu.broadcastChanges();
        if (!this.level().isClientSide && !this.containerMenu.stillValid((Player)this)) {
            this.closeContainer();
            this.containerMenu = this.inventoryMenu;
        }
        if ((entity = this.getCamera()) != this) {
            if (entity.isAlive()) {
                this.absMoveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                this.serverLevel().getChunkSource().move(this);
                if (this.wantsToStopRiding()) {
                    this.setCamera((Entity)this);
                }
            } else {
                this.setCamera((Entity)this);
            }
        }
        CriteriaTriggers.TICK.trigger(this);
        if (this.levitationStartPos != null) {
            CriteriaTriggers.LEVITATION.trigger(this, this.levitationStartPos, this.tickCount - this.levitationStartTime);
        }
        this.trackStartFallingPosition();
        this.trackEnteredOrExitedLavaOnVehicle();
        this.advancements.flushDirty(this);
    }

    public void doTick() {
        try {
            if (!this.isSpectator() || !this.touchingUnloadedChunk()) {
                super.tick();
            }
            for (int i = 0; i < this.getInventory().getContainerSize(); ++i) {
                Packet packet;
                ItemStack itemstack = this.getInventory().getItem(i);
                if (!itemstack.getItem().isComplex() || (packet = ((ComplexItem)itemstack.getItem()).getUpdatePacket(itemstack, this.level(), (Player)this)) == null) continue;
                this.connection.send(packet);
            }
            if (this.getHealth() != this.lastSentHealth || this.lastSentFood != this.foodData.getFoodLevel() || this.foodData.getSaturationLevel() == 0.0f != this.lastFoodSaturationZero) {
                this.connection.send((Packet)new ClientboundSetHealthPacket(this.getHealth(), this.foodData.getFoodLevel(), this.foodData.getSaturationLevel()));
                this.lastSentHealth = this.getHealth();
                this.lastSentFood = this.foodData.getFoodLevel();
                boolean bl = this.lastFoodSaturationZero = this.foodData.getSaturationLevel() == 0.0f;
            }
            if (this.getHealth() + this.getAbsorptionAmount() != this.lastRecordedHealthAndAbsorption) {
                this.lastRecordedHealthAndAbsorption = this.getHealth() + this.getAbsorptionAmount();
                this.updateScoreForCriteria(ObjectiveCriteria.HEALTH, Mth.ceil((float)this.lastRecordedHealthAndAbsorption));
            }
            if (this.foodData.getFoodLevel() != this.lastRecordedFoodLevel) {
                this.lastRecordedFoodLevel = this.foodData.getFoodLevel();
                this.updateScoreForCriteria(ObjectiveCriteria.FOOD, Mth.ceil((float)this.lastRecordedFoodLevel));
            }
            if (this.getAirSupply() != this.lastRecordedAirLevel) {
                this.lastRecordedAirLevel = this.getAirSupply();
                this.updateScoreForCriteria(ObjectiveCriteria.AIR, Mth.ceil((float)this.lastRecordedAirLevel));
            }
            if (this.getArmorValue() != this.lastRecordedArmor) {
                this.lastRecordedArmor = this.getArmorValue();
                this.updateScoreForCriteria(ObjectiveCriteria.ARMOR, Mth.ceil((float)this.lastRecordedArmor));
            }
            if (this.totalExperience != this.lastRecordedExperience) {
                this.lastRecordedExperience = this.totalExperience;
                this.updateScoreForCriteria(ObjectiveCriteria.EXPERIENCE, Mth.ceil((float)this.lastRecordedExperience));
            }
            if (this.experienceLevel != this.lastRecordedLevel) {
                this.lastRecordedLevel = this.experienceLevel;
                this.updateScoreForCriteria(ObjectiveCriteria.LEVEL, Mth.ceil((float)this.lastRecordedLevel));
            }
            if (this.totalExperience != this.lastSentExp) {
                this.lastSentExp = this.totalExperience;
                this.connection.send((Packet)new ClientboundSetExperiencePacket(this.experienceProgress, this.totalExperience, this.experienceLevel));
            }
            if (this.tickCount % 20 == 0) {
                CriteriaTriggers.LOCATION.trigger(this);
            }
        }
        catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.forThrowable((Throwable)throwable, (String)"Ticking player");
            CrashReportCategory crashreportcategory = crashreport.addCategory("Player being ticked");
            this.fillCrashReportCategory(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

    public void resetFallDistance() {
        if (this.getHealth() > 0.0f && this.startingToFallPosition != null) {
            CriteriaTriggers.FALL_FROM_HEIGHT.trigger(this, this.startingToFallPosition);
        }
        this.startingToFallPosition = null;
        super.resetFallDistance();
    }

    public void trackStartFallingPosition() {
        if (this.fallDistance > 0.0f && this.startingToFallPosition == null) {
            this.startingToFallPosition = this.position();
        }
    }

    public void trackEnteredOrExitedLavaOnVehicle() {
        if (this.getVehicle() != null && this.getVehicle().isInLava()) {
            if (this.enteredLavaOnVehiclePosition == null) {
                this.enteredLavaOnVehiclePosition = this.position();
            } else {
                CriteriaTriggers.RIDE_ENTITY_IN_LAVA_TRIGGER.trigger(this, this.enteredLavaOnVehiclePosition);
            }
        }
        if (!(this.enteredLavaOnVehiclePosition == null || this.getVehicle() != null && this.getVehicle().isInLava())) {
            this.enteredLavaOnVehiclePosition = null;
        }
    }

    private void updateScoreForCriteria(ObjectiveCriteria p_9105_, int p_9106_) {
        this.getScoreboard().forAllObjectives(p_9105_, this.getScoreboardName(), p_9178_ -> p_9178_.setScore(p_9106_));
    }

    public void die(DamageSource p_9035_) {
        this.gameEvent(GameEvent.ENTITY_DIE);
        if (ForgeHooks.onLivingDeath((LivingEntity)this, (DamageSource)p_9035_)) {
            return;
        }
        boolean flag = this.level().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES);
        if (flag) {
            Component component = this.getCombatTracker().getDeathMessage();
            this.connection.send((Packet)new ClientboundPlayerCombatKillPacket(this.getId(), component), PacketSendListener.exceptionallySend(() -> {
                int i = 256;
                String s = component.getString(256);
                MutableComponent component1 = Component.translatable((String)"death.attack.message_too_long", (Object[])new Object[]{Component.literal((String)s).withStyle(ChatFormatting.YELLOW)});
                MutableComponent component2 = Component.translatable((String)"death.attack.even_more_magic", (Object[])new Object[]{this.getDisplayName()}).withStyle(arg_0 -> ServerPlayer.lambda$die$4((Component)component1, arg_0));
                return new ClientboundPlayerCombatKillPacket(this.getId(), (Component)component2);
            }));
            Team team = this.getTeam();
            if (team != null && team.getDeathMessageVisibility() != Team.Visibility.ALWAYS) {
                if (team.getDeathMessageVisibility() == Team.Visibility.HIDE_FOR_OTHER_TEAMS) {
                    this.server.getPlayerList().broadcastSystemToTeam((Player)this, component);
                } else if (team.getDeathMessageVisibility() == Team.Visibility.HIDE_FOR_OWN_TEAM) {
                    this.server.getPlayerList().broadcastSystemToAllExceptTeam((Player)this, component);
                }
            } else {
                this.server.getPlayerList().broadcastSystemMessage(component, false);
            }
        } else {
            this.connection.send((Packet)new ClientboundPlayerCombatKillPacket(this.getId(), CommonComponents.EMPTY));
        }
        this.removeEntitiesOnShoulder();
        if (this.level().getGameRules().getBoolean(GameRules.RULE_FORGIVE_DEAD_PLAYERS)) {
            this.tellNeutralMobsThatIDied();
        }
        if (!this.isSpectator()) {
            this.dropAllDeathLoot(p_9035_);
        }
        this.getScoreboard().forAllObjectives(ObjectiveCriteria.DEATH_COUNT, this.getScoreboardName(), Score::increment);
        LivingEntity livingentity = this.getKillCredit();
        if (livingentity != null) {
            this.awardStat(Stats.ENTITY_KILLED_BY.get((Object)livingentity.getType()));
            livingentity.awardKillScore((Entity)this, this.deathScore, p_9035_);
            this.createWitherRose(livingentity);
        }
        this.level().broadcastEntityEvent((Entity)this, (byte)3);
        this.awardStat(Stats.DEATHS);
        this.resetStat(Stats.CUSTOM.get((Object)Stats.TIME_SINCE_DEATH));
        this.resetStat(Stats.CUSTOM.get((Object)Stats.TIME_SINCE_REST));
        this.clearFire();
        this.setTicksFrozen(0);
        this.setSharedFlagOnFire(false);
        this.getCombatTracker().recheckStatus();
        this.setLastDeathLocation(Optional.of(GlobalPos.of((ResourceKey)this.level().dimension(), (BlockPos)this.blockPosition())));
    }

    private void tellNeutralMobsThatIDied() {
        AABB aabb = new AABB(this.blockPosition()).inflate(32.0, 10.0, 32.0);
        this.level().getEntitiesOfClass(Mob.class, aabb, EntitySelector.NO_SPECTATORS).stream().filter(p_9188_ -> p_9188_ instanceof NeutralMob).forEach(p_9057_ -> ((NeutralMob)p_9057_).playerDied((Player)this));
    }

    public void awardKillScore(Entity p_9050_, int p_9051_, DamageSource p_9052_) {
        if (p_9050_ != this) {
            super.awardKillScore(p_9050_, p_9051_, p_9052_);
            this.increaseScore(p_9051_);
            String s = this.getScoreboardName();
            String s1 = p_9050_.getScoreboardName();
            this.getScoreboard().forAllObjectives(ObjectiveCriteria.KILL_COUNT_ALL, s, Score::increment);
            if (p_9050_ instanceof Player) {
                this.awardStat(Stats.PLAYER_KILLS);
                this.getScoreboard().forAllObjectives(ObjectiveCriteria.KILL_COUNT_PLAYERS, s, Score::increment);
            } else {
                this.awardStat(Stats.MOB_KILLS);
            }
            this.handleTeamKill(s, s1, ObjectiveCriteria.TEAM_KILL);
            this.handleTeamKill(s1, s, ObjectiveCriteria.KILLED_BY_TEAM);
            CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger(this, p_9050_, p_9052_);
        }
    }

    private void handleTeamKill(String p_9125_, String p_9126_, ObjectiveCriteria[] p_9127_) {
        int i;
        PlayerTeam playerteam = this.getScoreboard().getPlayersTeam(p_9126_);
        if (playerteam != null && (i = playerteam.getColor().getId()) >= 0 && i < p_9127_.length) {
            this.getScoreboard().forAllObjectives(p_9127_[i], p_9125_, Score::increment);
        }
    }

    public boolean hurt(DamageSource p_9037_, float p_9038_) {
        Player player1;
        AbstractArrow abstractarrow;
        Entity entity1;
        Player player;
        boolean flag;
        if (this.isInvulnerableTo(p_9037_)) {
            return false;
        }
        boolean bl = flag = this.server.isDedicatedServer() && this.isPvpAllowed() && p_9037_.is(DamageTypeTags.IS_FALL);
        if (!flag && this.spawnInvulnerableTime > 0 && !p_9037_.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }
        Entity entity = p_9037_.getEntity();
        if (entity instanceof Player && !this.canHarmPlayer(player = (Player)entity)) {
            return false;
        }
        if (entity instanceof AbstractArrow && (entity1 = (abstractarrow = (AbstractArrow)entity).getOwner()) instanceof Player && !this.canHarmPlayer(player1 = (Player)entity1)) {
            return false;
        }
        return super.hurt(p_9037_, p_9038_);
    }

    public boolean canHarmPlayer(Player p_9064_) {
        return !this.isPvpAllowed() ? false : super.canHarmPlayer(p_9064_);
    }

    private boolean isPvpAllowed() {
        return this.server.isPvpAllowed();
    }

    @Nullable
    protected PortalInfo findDimensionEntryPoint(ServerLevel p_8998_) {
        PortalInfo portalinfo = super.findDimensionEntryPoint(p_8998_);
        if (portalinfo != null && this.level().dimension() == Level.OVERWORLD && p_8998_.dimension() == Level.END) {
            Vec3 vec3 = portalinfo.pos.add(0.0, -1.0, 0.0);
            return new PortalInfo(vec3, Vec3.ZERO, 90.0f, 0.0f);
        }
        return portalinfo;
    }

    @Nullable
    public Entity changeDimension(ServerLevel p_9180_, ITeleporter teleporter) {
        if (!ForgeHooks.onTravelToDimension((Entity)this, (ResourceKey)p_9180_.dimension())) {
            return null;
        }
        this.isChangingDimension = true;
        ServerLevel serverlevel = this.serverLevel();
        ResourceKey resourcekey = serverlevel.dimension();
        if (resourcekey == Level.END && p_9180_.dimension() == Level.OVERWORLD && teleporter.isVanilla()) {
            this.unRide();
            this.serverLevel().removePlayerImmediately(this, Entity.RemovalReason.CHANGED_DIMENSION);
            if (!this.wonGame) {
                this.wonGame = true;
                this.connection.send((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, this.seenCredits ? 0.0f : 1.0f));
                this.seenCredits = true;
            }
            return this;
        }
        LevelData leveldata = p_9180_.getLevelData();
        this.connection.send((Packet)new ClientboundRespawnPacket(p_9180_.dimensionTypeId(), p_9180_.dimension(), BiomeManager.obfuscateSeed((long)p_9180_.getSeed()), this.gameMode.getGameModeForPlayer(), this.gameMode.getPreviousGameModeForPlayer(), p_9180_.isDebug(), p_9180_.isFlat(), 3, this.getLastDeathLocation(), this.getPortalCooldown()));
        this.connection.send((Packet)new ClientboundChangeDifficultyPacket(leveldata.getDifficulty(), leveldata.isDifficultyLocked()));
        PlayerList playerlist = this.server.getPlayerList();
        playerlist.sendPlayerPermissionLevel(this);
        serverlevel.removePlayerImmediately(this, Entity.RemovalReason.CHANGED_DIMENSION);
        this.revive();
        PortalInfo portalinfo = teleporter.getPortalInfo((Entity)this, p_9180_, this::findDimensionEntryPoint);
        if (portalinfo != null) {
            Entity e = teleporter.placeEntity((Entity)this, serverlevel, p_9180_, this.getYRot(), spawnPortal -> {
                serverlevel.getProfiler().push("moving");
                if (resourcekey == Level.OVERWORLD && p_9180_.dimension() == Level.NETHER) {
                    this.enteredNetherPosition = this.position();
                } else if (spawnPortal.booleanValue() && p_9180_.dimension() == Level.END) {
                    this.createEndPlatform(p_9180_, BlockPos.containing((Position)portalinfo.pos));
                }
                serverlevel.getProfiler().pop();
                serverlevel.getProfiler().push("placing");
                this.setServerLevel(p_9180_);
                this.connection.teleport(portalinfo.pos.x, portalinfo.pos.y, portalinfo.pos.z, portalinfo.yRot, portalinfo.xRot);
                this.connection.resetPosition();
                p_9180_.addDuringPortalTeleport(this);
                serverlevel.getProfiler().pop();
                this.triggerDimensionChangeTriggers(serverlevel);
                return this;
            });
            if (e != this) {
                throw new IllegalArgumentException(String.format(Locale.ENGLISH, "Teleporter %s returned not the player entity but instead %s, expected PlayerEntity %s", new Object[]{teleporter, e, this}));
            }
            this.connection.send((Packet)new ClientboundPlayerAbilitiesPacket(this.getAbilities()));
            playerlist.sendLevelInfo(this, p_9180_);
            playerlist.sendAllPlayerInfo(this);
            for (MobEffectInstance mobeffectinstance : this.getActiveEffects()) {
                this.connection.send((Packet)new ClientboundUpdateMobEffectPacket(this.getId(), mobeffectinstance));
            }
            if (teleporter.playTeleportSound(this, serverlevel, p_9180_)) {
                this.connection.send((Packet)new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
            }
            this.lastSentExp = -1;
            this.lastSentHealth = -1.0f;
            this.lastSentFood = -1;
            ForgeEventFactory.firePlayerChangedDimensionEvent((Player)this, (ResourceKey)resourcekey, (ResourceKey)p_9180_.dimension());
        }
        return this;
    }

    private void createEndPlatform(ServerLevel p_9007_, BlockPos p_9008_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = p_9008_.mutable();
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                for (int k = -1; k < 3; ++k) {
                    BlockState blockstate = k == -1 ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.AIR.defaultBlockState();
                    p_9007_.setBlockAndUpdate((BlockPos)blockpos$mutableblockpos.set((Vec3i)p_9008_).move(j, k, i), blockstate);
                }
            }
        }
    }

    protected Optional<BlockUtil.FoundRectangle> getExitPortal(ServerLevel p_184131_, BlockPos p_184132_, boolean p_184133_, WorldBorder p_184134_) {
        Optional optional = super.getExitPortal(p_184131_, p_184132_, p_184133_, p_184134_);
        if (optional.isPresent()) {
            return optional;
        }
        Direction.Axis direction$axis = this.level().getBlockState(this.portalEntrancePos).getOptionalValue((Property)NetherPortalBlock.AXIS).orElse(Direction.Axis.X);
        Optional optional1 = p_184131_.getPortalForcer().createPortal(p_184132_, direction$axis);
        if (!optional1.isPresent()) {
            LOGGER.error("Unable to create a portal, likely target out of worldborder");
        }
        return optional1;
    }

    private void triggerDimensionChangeTriggers(ServerLevel p_9210_) {
        ResourceKey resourcekey = p_9210_.dimension();
        ResourceKey resourcekey1 = this.level().dimension();
        CriteriaTriggers.CHANGED_DIMENSION.trigger(this, resourcekey, resourcekey1);
        if (resourcekey == Level.NETHER && resourcekey1 == Level.OVERWORLD && this.enteredNetherPosition != null) {
            CriteriaTriggers.NETHER_TRAVEL.trigger(this, this.enteredNetherPosition);
        }
        if (resourcekey1 != Level.NETHER) {
            this.enteredNetherPosition = null;
        }
    }

    public boolean broadcastToPlayer(ServerPlayer p_9014_) {
        if (p_9014_.isSpectator()) {
            return this.getCamera() == this;
        }
        return this.isSpectator() ? false : super.broadcastToPlayer(p_9014_);
    }

    public void take(Entity p_9047_, int p_9048_) {
        super.take(p_9047_, p_9048_);
        this.containerMenu.broadcastChanges();
    }

    public Either<Player.BedSleepingProblem, Unit> startSleepInBed(BlockPos p_9115_) {
        Optional<BlockPos> optAt = Optional.of(p_9115_);
        Player.BedSleepingProblem ret = ForgeEventFactory.onPlayerSleepInBed((Player)this, optAt);
        if (ret != null) {
            return Either.left((Object)ret);
        }
        Direction direction = (Direction)this.level().getBlockState(p_9115_).getValue((Property)HorizontalDirectionalBlock.FACING);
        if (!this.isSleeping() && this.isAlive()) {
            if (!this.level().dimensionType().natural()) {
                return Either.left((Object)Player.BedSleepingProblem.NOT_POSSIBLE_HERE);
            }
            if (!this.bedInRange(p_9115_, direction)) {
                return Either.left((Object)Player.BedSleepingProblem.TOO_FAR_AWAY);
            }
            if (this.bedBlocked(p_9115_, direction)) {
                return Either.left((Object)Player.BedSleepingProblem.OBSTRUCTED);
            }
            this.setRespawnPosition((ResourceKey<Level>)this.level().dimension(), p_9115_, this.getYRot(), false, true);
            if (!ForgeEventFactory.fireSleepingTimeCheck((Player)this, optAt)) {
                return Either.left((Object)Player.BedSleepingProblem.NOT_POSSIBLE_NOW);
            }
            if (!this.isCreative()) {
                double d0 = 8.0;
                double d1 = 5.0;
                Vec3 vec3 = Vec3.atBottomCenterOf((Vec3i)p_9115_);
                List list = this.level().getEntitiesOfClass(Monster.class, new AABB(vec3.x() - 8.0, vec3.y() - 5.0, vec3.z() - 8.0, vec3.x() + 8.0, vec3.y() + 5.0, vec3.z() + 8.0), p_9062_ -> p_9062_.isPreventingPlayerRest((Player)this));
                if (!list.isEmpty()) {
                    return Either.left((Object)Player.BedSleepingProblem.NOT_SAFE);
                }
            }
            Either either = super.startSleepInBed(p_9115_).ifRight(p_9029_ -> {
                this.awardStat(Stats.SLEEP_IN_BED);
                CriteriaTriggers.SLEPT_IN_BED.trigger(this);
            });
            if (!this.serverLevel().canSleepThroughNights()) {
                this.displayClientMessage((Component)Component.translatable((String)"sleep.not_possible"), true);
            }
            ((ServerLevel)this.level()).updateSleepingPlayerList();
            return either;
        }
        return Either.left((Object)Player.BedSleepingProblem.OTHER_PROBLEM);
    }

    public void startSleeping(BlockPos p_9190_) {
        this.resetStat(Stats.CUSTOM.get((Object)Stats.TIME_SINCE_REST));
        super.startSleeping(p_9190_);
    }

    private boolean bedInRange(BlockPos p_9117_, Direction p_9118_) {
        if (p_9118_ == null) {
            return false;
        }
        return this.isReachableBedBlock(p_9117_) || this.isReachableBedBlock(p_9117_.relative(p_9118_.getOpposite()));
    }

    private boolean isReachableBedBlock(BlockPos p_9223_) {
        Vec3 vec3 = Vec3.atBottomCenterOf((Vec3i)p_9223_);
        return Math.abs(this.getX() - vec3.x()) <= 3.0 && Math.abs(this.getY() - vec3.y()) <= 2.0 && Math.abs(this.getZ() - vec3.z()) <= 3.0;
    }

    private boolean bedBlocked(BlockPos p_9192_, Direction p_9193_) {
        BlockPos blockpos = p_9192_.above();
        return !this.freeAt(blockpos) || !this.freeAt(blockpos.relative(p_9193_.getOpposite()));
    }

    public void stopSleepInBed(boolean p_9165_, boolean p_9166_) {
        if (this.isSleeping()) {
            this.serverLevel().getChunkSource().broadcastAndSend((Entity)this, (Packet)new ClientboundAnimatePacket((Entity)this, 2));
        }
        super.stopSleepInBed(p_9165_, p_9166_);
        if (this.connection != null) {
            this.connection.teleport(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
        }
    }

    public void dismountTo(double p_143389_, double p_143390_, double p_143391_) {
        this.removeVehicle();
        this.setPos(p_143389_, p_143390_, p_143391_);
    }

    public boolean isInvulnerableTo(DamageSource p_9182_) {
        return super.isInvulnerableTo(p_9182_) || this.isChangingDimension();
    }

    protected void checkFallDamage(double p_8976_, boolean p_8977_, BlockState p_8978_, BlockPos p_8979_) {
    }

    protected void onChangedBlock(BlockPos p_9206_) {
        if (!this.isSpectator()) {
            super.onChangedBlock(p_9206_);
        }
    }

    public void doCheckFallDamage(double p_289676_, double p_289671_, double p_289665_, boolean p_289696_) {
        if (!this.touchingUnloadedChunk()) {
            this.checkSupportingBlock(p_289696_, new Vec3(p_289676_, p_289671_, p_289665_));
            BlockPos blockpos = this.getOnPosLegacy();
            super.checkFallDamage(p_289671_, p_289696_, this.level().getBlockState(blockpos), blockpos);
        }
    }

    public void openTextEdit(SignBlockEntity p_277909_, boolean p_277495_) {
        this.connection.send((Packet)new ClientboundBlockUpdatePacket((BlockGetter)this.level(), p_277909_.getBlockPos()));
        this.connection.send((Packet)new ClientboundOpenSignEditorPacket(p_277909_.getBlockPos(), p_277495_));
    }

    public void nextContainerCounter() {
        this.containerCounter = this.containerCounter % 100 + 1;
    }

    public OptionalInt openMenu(@Nullable MenuProvider p_9033_) {
        if (p_9033_ == null) {
            return OptionalInt.empty();
        }
        if (this.containerMenu != this.inventoryMenu) {
            this.closeContainer();
        }
        this.nextContainerCounter();
        AbstractContainerMenu abstractcontainermenu = p_9033_.createMenu(this.containerCounter, this.getInventory(), (Player)this);
        if (abstractcontainermenu == null) {
            if (this.isSpectator()) {
                this.displayClientMessage((Component)Component.translatable((String)"container.spectatorCantOpen").withStyle(ChatFormatting.RED), true);
            }
            return OptionalInt.empty();
        }
        this.connection.send((Packet)new ClientboundOpenScreenPacket(abstractcontainermenu.containerId, abstractcontainermenu.getType(), p_9033_.getDisplayName()));
        this.initMenu(abstractcontainermenu);
        this.containerMenu = abstractcontainermenu;
        MinecraftForge.EVENT_BUS.post((Event)new PlayerContainerEvent.Open((Player)this, this.containerMenu));
        return OptionalInt.of(this.containerCounter);
    }

    public void sendMerchantOffers(int p_8988_, MerchantOffers p_8989_, int p_8990_, int p_8991_, boolean p_8992_, boolean p_8993_) {
        this.connection.send((Packet)new ClientboundMerchantOffersPacket(p_8988_, p_8989_, p_8990_, p_8991_, p_8992_, p_8993_));
    }

    public void openHorseInventory(AbstractHorse p_9059_, Container p_9060_) {
        if (this.containerMenu != this.inventoryMenu) {
            this.closeContainer();
        }
        this.nextContainerCounter();
        this.connection.send((Packet)new ClientboundHorseScreenOpenPacket(this.containerCounter, p_9060_.getContainerSize(), p_9059_.getId()));
        this.containerMenu = new HorseInventoryMenu(this.containerCounter, this.getInventory(), p_9060_, p_9059_);
        this.initMenu(this.containerMenu);
        MinecraftForge.EVENT_BUS.post((Event)new PlayerContainerEvent.Open((Player)this, this.containerMenu));
    }

    public void openItemGui(ItemStack p_9082_, InteractionHand p_9083_) {
        if (p_9082_.is(Items.WRITTEN_BOOK)) {
            if (WrittenBookItem.resolveBookComponents((ItemStack)p_9082_, (CommandSourceStack)this.createCommandSourceStack(), (Player)this)) {
                this.containerMenu.broadcastChanges();
            }
            this.connection.send((Packet)new ClientboundOpenBookPacket(p_9083_));
        }
    }

    public void openCommandBlock(CommandBlockEntity p_9099_) {
        this.connection.send((Packet)ClientboundBlockEntityDataPacket.create((BlockEntity)p_9099_, BlockEntity::saveWithoutMetadata));
    }

    public void closeContainer() {
        this.connection.send((Packet)new ClientboundContainerClosePacket(this.containerMenu.containerId));
        this.doCloseContainer();
    }

    public void doCloseContainer() {
        this.containerMenu.removed((Player)this);
        this.inventoryMenu.transferState(this.containerMenu);
        MinecraftForge.EVENT_BUS.post((Event)new PlayerContainerEvent.Close((Player)this, this.containerMenu));
        this.containerMenu = this.inventoryMenu;
    }

    public void setPlayerInput(float p_8981_, float p_8982_, boolean p_8983_, boolean p_8984_) {
        if (this.isPassenger()) {
            if (p_8981_ >= -1.0f && p_8981_ <= 1.0f) {
                this.xxa = p_8981_;
            }
            if (p_8982_ >= -1.0f && p_8982_ <= 1.0f) {
                this.zza = p_8982_;
            }
            this.jumping = p_8983_;
            this.setShiftKeyDown(p_8984_);
        }
    }

    public void awardStat(Stat<?> p_9026_, int p_9027_) {
        this.stats.increment((Player)this, p_9026_, p_9027_);
        this.getScoreboard().forAllObjectives(p_9026_, this.getScoreboardName(), p_8996_ -> p_8996_.add(p_9027_));
    }

    public void resetStat(Stat<?> p_9024_) {
        this.stats.setValue((Player)this, p_9024_, 0);
        this.getScoreboard().forAllObjectives(p_9024_, this.getScoreboardName(), Score::reset);
    }

    public int awardRecipes(Collection<Recipe<?>> p_9129_) {
        return this.recipeBook.addRecipes(p_9129_, this);
    }

    public void triggerRecipeCrafted(Recipe<?> p_283176_, List<ItemStack> p_282336_) {
        CriteriaTriggers.RECIPE_CRAFTED.trigger(this, p_283176_.getId(), p_282336_);
    }

    public void awardRecipesByKey(ResourceLocation[] p_9168_) {
        ArrayList list = Lists.newArrayList();
        for (ResourceLocation resourcelocation : p_9168_) {
            this.server.getRecipeManager().byKey(resourcelocation).ifPresent(list::add);
        }
        this.awardRecipes(list);
    }

    public int resetRecipes(Collection<Recipe<?>> p_9195_) {
        return this.recipeBook.removeRecipes(p_9195_, this);
    }

    public void giveExperiencePoints(int p_9208_) {
        super.giveExperiencePoints(p_9208_);
        this.lastSentExp = -1;
    }

    public void disconnect() {
        this.disconnected = true;
        this.ejectPassengers();
        if (this.isSleeping()) {
            this.stopSleepInBed(true, false);
        }
    }

    public boolean hasDisconnected() {
        return this.disconnected;
    }

    public void resetSentInfo() {
        this.lastSentHealth = -1.0E8f;
    }

    public void displayClientMessage(Component p_9154_, boolean p_9155_) {
        this.sendSystemMessage(p_9154_, p_9155_);
    }

    protected void completeUsingItem() {
        if (!this.useItem.isEmpty() && this.isUsingItem()) {
            this.connection.send((Packet)new ClientboundEntityEventPacket((Entity)this, 9));
            super.completeUsingItem();
        }
    }

    public void lookAt(EntityAnchorArgument.Anchor p_9112_, Vec3 p_9113_) {
        super.lookAt(p_9112_, p_9113_);
        this.connection.send((Packet)new ClientboundPlayerLookAtPacket(p_9112_, p_9113_.x, p_9113_.y, p_9113_.z));
    }

    public void lookAt(EntityAnchorArgument.Anchor p_9108_, Entity p_9109_, EntityAnchorArgument.Anchor p_9110_) {
        Vec3 vec3 = p_9110_.apply(p_9109_);
        super.lookAt(p_9108_, vec3);
        this.connection.send((Packet)new ClientboundPlayerLookAtPacket(p_9108_, p_9109_, p_9110_));
    }

    public void restoreFrom(ServerPlayer p_9016_, boolean p_9017_) {
        this.wardenSpawnTracker = p_9016_.wardenSpawnTracker;
        this.textFilteringEnabled = p_9016_.textFilteringEnabled;
        this.chatSession = p_9016_.chatSession;
        this.gameMode.setGameModeForPlayer(p_9016_.gameMode.getGameModeForPlayer(), p_9016_.gameMode.getPreviousGameModeForPlayer());
        this.onUpdateAbilities();
        if (p_9017_) {
            this.getInventory().replaceWith(p_9016_.getInventory());
            this.setHealth(p_9016_.getHealth());
            this.foodData = p_9016_.foodData;
            this.experienceLevel = p_9016_.experienceLevel;
            this.totalExperience = p_9016_.totalExperience;
            this.experienceProgress = p_9016_.experienceProgress;
            this.setScore(p_9016_.getScore());
            this.portalEntrancePos = p_9016_.portalEntrancePos;
        } else if (this.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) || p_9016_.isSpectator()) {
            this.getInventory().replaceWith(p_9016_.getInventory());
            this.experienceLevel = p_9016_.experienceLevel;
            this.totalExperience = p_9016_.totalExperience;
            this.experienceProgress = p_9016_.experienceProgress;
            this.setScore(p_9016_.getScore());
        }
        this.enchantmentSeed = p_9016_.enchantmentSeed;
        this.enderChestInventory = p_9016_.enderChestInventory;
        this.getEntityData().set(DATA_PLAYER_MODE_CUSTOMISATION, (Object)((Byte)p_9016_.getEntityData().get(DATA_PLAYER_MODE_CUSTOMISATION)));
        this.lastSentExp = -1;
        this.lastSentHealth = -1.0f;
        this.lastSentFood = -1;
        this.recipeBook.copyOverData((RecipeBook)p_9016_.recipeBook);
        this.seenCredits = p_9016_.seenCredits;
        this.enteredNetherPosition = p_9016_.enteredNetherPosition;
        this.setShoulderEntityLeft(p_9016_.getShoulderEntityLeft());
        this.setShoulderEntityRight(p_9016_.getShoulderEntityRight());
        this.setLastDeathLocation(p_9016_.getLastDeathLocation());
        CompoundTag old = p_9016_.getPersistentData();
        if (old.contains("PlayerPersisted")) {
            this.getPersistentData().put("PlayerPersisted", old.get("PlayerPersisted"));
        }
        ForgeEventFactory.onPlayerClone((Player)this, (Player)p_9016_, (!p_9017_ ? 1 : 0) != 0);
        this.tabListHeader = p_9016_.tabListHeader;
        this.tabListFooter = p_9016_.tabListFooter;
    }

    protected void onEffectAdded(MobEffectInstance p_143393_, @Nullable Entity p_143394_) {
        super.onEffectAdded(p_143393_, p_143394_);
        this.connection.send((Packet)new ClientboundUpdateMobEffectPacket(this.getId(), p_143393_));
        if (p_143393_.getEffect() == MobEffects.LEVITATION) {
            this.levitationStartTime = this.tickCount;
            this.levitationStartPos = this.position();
        }
        CriteriaTriggers.EFFECTS_CHANGED.trigger(this, p_143394_);
    }

    protected void onEffectUpdated(MobEffectInstance p_143396_, boolean p_143397_, @Nullable Entity p_143398_) {
        super.onEffectUpdated(p_143396_, p_143397_, p_143398_);
        this.connection.send((Packet)new ClientboundUpdateMobEffectPacket(this.getId(), p_143396_));
        CriteriaTriggers.EFFECTS_CHANGED.trigger(this, p_143398_);
    }

    protected void onEffectRemoved(MobEffectInstance p_9184_) {
        super.onEffectRemoved(p_9184_);
        this.connection.send((Packet)new ClientboundRemoveMobEffectPacket(this.getId(), p_9184_.getEffect()));
        if (p_9184_.getEffect() == MobEffects.LEVITATION) {
            this.levitationStartPos = null;
        }
        CriteriaTriggers.EFFECTS_CHANGED.trigger(this, (Entity)null);
    }

    public void teleportTo(double p_8969_, double p_8970_, double p_8971_) {
        this.connection.teleport(p_8969_, p_8970_, p_8971_, this.getYRot(), this.getXRot(), RelativeMovement.ROTATION);
    }

    public void teleportRelative(double p_251611_, double p_248861_, double p_252266_) {
        this.connection.teleport(this.getX() + p_251611_, this.getY() + p_248861_, this.getZ() + p_252266_, this.getYRot(), this.getXRot(), RelativeMovement.ALL);
    }

    public boolean teleportTo(ServerLevel p_265564_, double p_265424_, double p_265680_, double p_265312_, Set<RelativeMovement> p_265192_, float p_265059_, float p_265266_) {
        ChunkPos chunkpos = new ChunkPos(BlockPos.containing((double)p_265424_, (double)p_265680_, (double)p_265312_));
        p_265564_.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, chunkpos, 1, (Object)this.getId());
        this.stopRiding();
        if (this.isSleeping()) {
            this.stopSleepInBed(true, true);
        }
        if (p_265564_ == this.level()) {
            this.connection.teleport(p_265424_, p_265680_, p_265312_, p_265059_, p_265266_, p_265192_);
        } else {
            this.teleportTo(p_265564_, p_265424_, p_265680_, p_265312_, p_265059_, p_265266_);
        }
        this.setYHeadRot(p_265059_);
        return true;
    }

    public void moveTo(double p_9171_, double p_9172_, double p_9173_) {
        super.moveTo(p_9171_, p_9172_, p_9173_);
        this.connection.resetPosition();
    }

    public void crit(Entity p_9045_) {
        this.serverLevel().getChunkSource().broadcastAndSend((Entity)this, (Packet)new ClientboundAnimatePacket(p_9045_, 4));
    }

    public void magicCrit(Entity p_9186_) {
        this.serverLevel().getChunkSource().broadcastAndSend((Entity)this, (Packet)new ClientboundAnimatePacket(p_9186_, 5));
    }

    public void onUpdateAbilities() {
        if (this.connection != null) {
            this.connection.send((Packet)new ClientboundPlayerAbilitiesPacket(this.getAbilities()));
            this.updateInvisibilityStatus();
        }
    }

    public ServerLevel serverLevel() {
        return (ServerLevel)this.level();
    }

    public boolean setGameMode(GameType p_143404_) {
        p_143404_ = ForgeHooks.onChangeGameType((Player)this, (GameType)this.gameMode.getGameModeForPlayer(), (GameType)p_143404_);
        if (p_143404_ == null) {
            return false;
        }
        if (!this.gameMode.changeGameModeForPlayer(p_143404_)) {
            return false;
        }
        this.connection.send((Packet)new ClientboundGameEventPacket(ClientboundGameEventPacket.CHANGE_GAME_MODE, (float)p_143404_.getId()));
        if (p_143404_ == GameType.SPECTATOR) {
            this.removeEntitiesOnShoulder();
            this.stopRiding();
        } else {
            this.setCamera((Entity)this);
        }
        this.onUpdateAbilities();
        this.updateEffectVisibility();
        return true;
    }

    public boolean isSpectator() {
        return this.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
    }

    public boolean isCreative() {
        return this.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
    }

    public void sendSystemMessage(Component p_215097_) {
        this.sendSystemMessage(p_215097_, false);
    }

    public void sendSystemMessage(Component p_240560_, boolean p_240545_) {
        if (this.acceptsSystemMessages(p_240545_)) {
            this.connection.send((Packet)new ClientboundSystemChatPacket(p_240560_, p_240545_), PacketSendListener.exceptionallySend(() -> {
                if (this.acceptsSystemMessages(false)) {
                    int i = 256;
                    String s = p_240560_.getString(256);
                    MutableComponent component = Component.literal((String)s).withStyle(ChatFormatting.YELLOW);
                    return new ClientboundSystemChatPacket((Component)Component.translatable((String)"multiplayer.message_not_delivered", (Object[])new Object[]{component}).withStyle(ChatFormatting.RED), false);
                }
                return null;
            }));
        }
    }

    public void sendChatMessage(OutgoingChatMessage p_249852_, boolean p_250110_, ChatType.Bound p_252108_) {
        if (this.acceptsChatMessages()) {
            p_249852_.sendToPlayer(this, p_250110_, p_252108_);
        }
    }

    public String getIpAddress() {
        SocketAddress socketaddress = this.connection.getRemoteAddress();
        if (socketaddress instanceof InetSocketAddress) {
            InetSocketAddress inetsocketaddress = (InetSocketAddress)socketaddress;
            return InetAddresses.toAddrString((InetAddress)inetsocketaddress.getAddress());
        }
        return "<unknown>";
    }

    public void updateOptions(ServerboundClientInformationPacket p_9157_) {
        this.chatVisibility = p_9157_.chatVisibility();
        this.canChatColor = p_9157_.chatColors();
        this.textFilteringEnabled = p_9157_.textFilteringEnabled();
        this.allowsListing = p_9157_.allowsListing();
        this.getEntityData().set(DATA_PLAYER_MODE_CUSTOMISATION, (Object)((byte)p_9157_.modelCustomisation()));
        this.getEntityData().set(DATA_PLAYER_MAIN_HAND, (Object)((byte)(p_9157_.mainHand() != HumanoidArm.LEFT ? 1 : 0)));
        this.language = p_9157_.language();
    }

    public boolean canChatInColor() {
        return this.canChatColor;
    }

    public ChatVisiblity getChatVisibility() {
        return this.chatVisibility;
    }

    private boolean acceptsSystemMessages(boolean p_240568_) {
        return this.chatVisibility == ChatVisiblity.HIDDEN ? p_240568_ : true;
    }

    private boolean acceptsChatMessages() {
        return this.chatVisibility == ChatVisiblity.FULL;
    }

    public void sendTexturePack(String p_143409_, String p_143410_, boolean p_143411_, @Nullable Component p_143412_) {
        this.connection.send((Packet)new ClientboundResourcePackPacket(p_143409_, p_143410_, p_143411_, p_143412_));
    }

    public void sendServerStatus(ServerStatus p_215110_) {
        this.connection.send((Packet)new ClientboundServerDataPacket(p_215110_.description(), p_215110_.favicon().map(ServerStatus.Favicon::iconBytes), p_215110_.enforcesSecureChat()));
    }

    protected int getPermissionLevel() {
        return this.server.getProfilePermissions(this.getGameProfile());
    }

    public void resetLastActionTime() {
        this.lastActionTime = Util.getMillis();
    }

    public ServerStatsCounter getStats() {
        return this.stats;
    }

    public ServerRecipeBook getRecipeBook() {
        return this.recipeBook;
    }

    protected void updateInvisibilityStatus() {
        if (this.isSpectator()) {
            this.removeEffectParticles();
            this.setInvisible(true);
        } else {
            super.updateInvisibilityStatus();
        }
    }

    public Entity getCamera() {
        return this.camera == null ? this : this.camera;
    }

    public void setCamera(@Nullable Entity p_9214_) {
        Entity entity;
        Entity entity2 = this.getCamera();
        ServerPlayer serverPlayer = this.camera = p_9214_ == null ? this : p_9214_;
        while ((entity = this.camera) instanceof PartEntity) {
            PartEntity partEntity = (PartEntity)entity;
            this.camera = partEntity.getParent();
        }
        if (entity2 != this.camera) {
            Level level = this.camera.level();
            if (level instanceof ServerLevel) {
                ServerLevel serverlevel = (ServerLevel)level;
                this.teleportTo(serverlevel, this.camera.getX(), this.camera.getY(), this.camera.getZ(), Set.of(), this.getYRot(), this.getXRot());
            }
            if (p_9214_ != null) {
                this.serverLevel().getChunkSource().move(this);
            }
            this.connection.send((Packet)new ClientboundSetCameraPacket(this.camera));
            this.connection.resetPosition();
        }
    }

    protected void processPortalCooldown() {
        if (!this.isChangingDimension) {
            super.processPortalCooldown();
        }
    }

    public void attack(Entity p_9220_) {
        if (this.gameMode.getGameModeForPlayer() == GameType.SPECTATOR) {
            this.setCamera(p_9220_);
        } else {
            super.attack(p_9220_);
        }
    }

    public long getLastActionTime() {
        return this.lastActionTime;
    }

    @Nullable
    public Component getTabListDisplayName() {
        if (!this.hasTabListName) {
            this.tabListDisplayName = ForgeEventFactory.getPlayerTabListDisplayName((Player)this);
            this.hasTabListName = true;
        }
        return this.tabListDisplayName;
    }

    public void swing(InteractionHand p_9031_) {
        super.swing(p_9031_);
        this.resetAttackStrengthTicker();
    }

    public boolean isChangingDimension() {
        return this.isChangingDimension;
    }

    public void hasChangedDimension() {
        this.isChangingDimension = false;
    }

    public PlayerAdvancements getAdvancements() {
        return this.advancements;
    }

    public void teleportTo(ServerLevel p_9000_, double p_9001_, double p_9002_, double p_9003_, float p_9004_, float p_9005_) {
        this.setCamera((Entity)this);
        this.stopRiding();
        if (p_9000_ == this.level()) {
            this.connection.teleport(p_9001_, p_9002_, p_9003_, p_9004_, p_9005_);
        } else if (ForgeHooks.onTravelToDimension((Entity)this, (ResourceKey)p_9000_.dimension())) {
            ServerLevel serverlevel = this.serverLevel();
            LevelData leveldata = p_9000_.getLevelData();
            this.connection.send((Packet)new ClientboundRespawnPacket(p_9000_.dimensionTypeId(), p_9000_.dimension(), BiomeManager.obfuscateSeed((long)p_9000_.getSeed()), this.gameMode.getGameModeForPlayer(), this.gameMode.getPreviousGameModeForPlayer(), p_9000_.isDebug(), p_9000_.isFlat(), 3, this.getLastDeathLocation(), this.getPortalCooldown()));
            this.connection.send((Packet)new ClientboundChangeDifficultyPacket(leveldata.getDifficulty(), leveldata.isDifficultyLocked()));
            this.server.getPlayerList().sendPlayerPermissionLevel(this);
            serverlevel.removePlayerImmediately(this, Entity.RemovalReason.CHANGED_DIMENSION);
            this.revive();
            this.moveTo(p_9001_, p_9002_, p_9003_, p_9004_, p_9005_);
            this.setServerLevel(p_9000_);
            p_9000_.addDuringCommandTeleport(this);
            this.triggerDimensionChangeTriggers(serverlevel);
            this.connection.teleport(p_9001_, p_9002_, p_9003_, p_9004_, p_9005_);
            this.gameMode.setLevel(p_9000_);
            this.server.getPlayerList().sendLevelInfo(this, p_9000_);
            this.server.getPlayerList().sendAllPlayerInfo(this);
            ForgeEventFactory.firePlayerChangedDimensionEvent((Player)this, (ResourceKey)serverlevel.dimension(), (ResourceKey)p_9000_.dimension());
        }
    }

    @Nullable
    public BlockPos getRespawnPosition() {
        return this.respawnPosition;
    }

    public float getRespawnAngle() {
        return this.respawnAngle;
    }

    public ResourceKey<Level> getRespawnDimension() {
        return this.respawnDimension;
    }

    public boolean isRespawnForced() {
        return this.respawnForced;
    }

    public void setRespawnPosition(ResourceKey<Level> p_9159_, @Nullable BlockPos p_9160_, float p_9161_, boolean p_9162_, boolean p_9163_) {
        if (ForgeEventFactory.onPlayerSpawnSet((Player)this, p_9160_ == null ? Level.OVERWORLD : p_9159_, (BlockPos)p_9160_, (boolean)p_9162_)) {
            return;
        }
        if (p_9160_ != null) {
            boolean flag;
            boolean bl = flag = p_9160_.equals((Object)this.respawnPosition) && p_9159_.equals(this.respawnDimension);
            if (p_9163_ && !flag) {
                this.sendSystemMessage((Component)Component.translatable((String)"block.minecraft.set_spawn"));
            }
            this.respawnPosition = p_9160_;
            this.respawnDimension = p_9159_;
            this.respawnAngle = p_9161_;
            this.respawnForced = p_9162_;
        } else {
            this.respawnPosition = null;
            this.respawnDimension = Level.OVERWORLD;
            this.respawnAngle = 0.0f;
            this.respawnForced = false;
        }
    }

    public void trackChunk(ChunkPos p_184136_, Packet<?> p_184137_) {
        this.connection.send(p_184137_);
    }

    public void untrackChunk(ChunkPos p_9089_) {
        if (this.isAlive()) {
            this.connection.send((Packet)new ClientboundForgetLevelChunkPacket(p_9089_.x, p_9089_.z));
        }
    }

    public SectionPos getLastSectionPos() {
        return this.lastSectionPos;
    }

    public void setLastSectionPos(SectionPos p_9120_) {
        this.lastSectionPos = p_9120_;
    }

    public void playNotifySound(SoundEvent p_9019_, SoundSource p_9020_, float p_9021_, float p_9022_) {
        this.connection.send((Packet)new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.wrapAsHolder((Object)p_9019_), p_9020_, this.getX(), this.getY(), this.getZ(), p_9021_, p_9022_, this.random.nextLong()));
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddPlayerPacket((Player)this);
    }

    public ItemEntity drop(ItemStack p_9085_, boolean p_9086_, boolean p_9087_) {
        ItemEntity itementity = super.drop(p_9085_, p_9086_, p_9087_);
        if (itementity == null) {
            return null;
        }
        if (this.captureDrops() != null) {
            this.captureDrops().add(itementity);
        } else {
            this.level().addFreshEntity((Entity)itementity);
        }
        ItemStack itemstack = itementity.getItem();
        if (p_9087_) {
            if (!itemstack.isEmpty()) {
                this.awardStat(Stats.ITEM_DROPPED.get((Object)itemstack.getItem()), p_9085_.getCount());
            }
            this.awardStat(Stats.DROP);
        }
        return itementity;
    }

    public String getLanguage() {
        return this.language;
    }

    public Component getTabListHeader() {
        return this.tabListHeader;
    }

    public void setTabListHeader(Component header) {
        this.setTabListHeaderFooter(header, this.tabListFooter);
    }

    public Component getTabListFooter() {
        return this.tabListFooter;
    }

    public void setTabListFooter(Component footer) {
        this.setTabListHeaderFooter(this.tabListHeader, footer);
    }

    public void setTabListHeaderFooter(Component header, Component footer) {
        if (Objects.equals(header, this.tabListHeader) && Objects.equals(footer, this.tabListFooter)) {
            return;
        }
        this.tabListHeader = Objects.requireNonNull(header, "header");
        this.tabListFooter = Objects.requireNonNull(footer, "footer");
        this.connection.send((Packet)new ClientboundTabListPacket(header, footer));
    }

    public void refreshTabListName() {
        Component oldName = this.tabListDisplayName;
        this.tabListDisplayName = ForgeEventFactory.getPlayerTabListDisplayName((Player)this);
        if (!Objects.equals(oldName, this.tabListDisplayName)) {
            this.getServer().getPlayerList().broadcastAll((Packet)new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME, this));
        }
    }

    public TextFilter getTextFilter() {
        return this.textFilter;
    }

    public void setServerLevel(ServerLevel p_284971_) {
        this.setLevel((Level)p_284971_);
        this.gameMode.setLevel(p_284971_);
    }

    @Nullable
    private static GameType readPlayerMode(@Nullable CompoundTag p_143414_, String p_143415_) {
        return p_143414_ != null && p_143414_.contains(p_143415_, 99) ? GameType.byId((int)p_143414_.getInt(p_143415_)) : null;
    }

    private GameType calculateGameModeForNewPlayer(@Nullable GameType p_143424_) {
        GameType gametype = this.server.getForcedGameType();
        if (gametype != null) {
            return gametype;
        }
        return p_143424_ != null ? p_143424_ : this.server.getDefaultGameType();
    }

    public void loadGameTypes(@Nullable CompoundTag p_143428_) {
        this.gameMode.setGameModeForPlayer(this.calculateGameModeForNewPlayer(ServerPlayer.readPlayerMode(p_143428_, "playerGameType")), ServerPlayer.readPlayerMode(p_143428_, "previousPlayerGameType"));
    }

    private void storeGameTypes(CompoundTag p_143431_) {
        p_143431_.putInt("playerGameType", this.gameMode.getGameModeForPlayer().getId());
        GameType gametype = this.gameMode.getPreviousGameModeForPlayer();
        if (gametype != null) {
            p_143431_.putInt("previousPlayerGameType", gametype.getId());
        }
    }

    public boolean isTextFilteringEnabled() {
        return this.textFilteringEnabled;
    }

    public boolean shouldFilterMessageTo(ServerPlayer p_143422_) {
        if (p_143422_ == this) {
            return false;
        }
        return this.textFilteringEnabled || p_143422_.textFilteringEnabled;
    }

    public boolean mayInteract(Level p_143406_, BlockPos p_143407_) {
        return super.mayInteract(p_143406_, p_143407_) && p_143406_.mayInteract((Player)this, p_143407_);
    }

    protected void updateUsingItem(ItemStack p_143402_) {
        CriteriaTriggers.USING_ITEM.trigger(this, p_143402_);
        super.updateUsingItem(p_143402_);
    }

    public boolean drop(boolean p_182295_) {
        Inventory inventory = this.getInventory();
        ItemStack selected = inventory.getSelected();
        if (selected.isEmpty() || !selected.onDroppedByPlayer((Player)this)) {
            return false;
        }
        if (this.isUsingItem() && this.getUsedItemHand() == InteractionHand.MAIN_HAND && (p_182295_ || selected.getCount() == 1)) {
            this.stopUsingItem();
        }
        ItemStack itemstack = inventory.removeFromSelected(p_182295_);
        this.containerMenu.findSlot((Container)inventory, inventory.selected).ifPresent(p_287377_ -> this.containerMenu.setRemoteSlot(p_287377_, inventory.getSelected()));
        return ForgeHooks.onPlayerTossEvent((Player)this, (ItemStack)itemstack, (boolean)true) != null;
    }

    public boolean allowsListing() {
        return this.allowsListing;
    }

    public Optional<WardenSpawnTracker> getWardenSpawnTracker() {
        return Optional.of(this.wardenSpawnTracker);
    }

    public void onItemPickup(ItemEntity p_215095_) {
        super.onItemPickup(p_215095_);
        Entity entity = p_215095_.getOwner();
        if (entity != null) {
            CriteriaTriggers.THROWN_ITEM_PICKED_UP_BY_PLAYER.trigger(this, p_215095_.getItem(), entity);
        }
    }

    public void setChatSession(RemoteChatSession p_254468_) {
        this.chatSession = p_254468_;
    }

    @Nullable
    public RemoteChatSession getChatSession() {
        return this.chatSession != null && this.chatSession.hasExpired() ? null : this.chatSession;
    }

    public void indicateDamage(double p_270621_, double p_270478_) {
        this.hurtDir = (float)(Mth.atan2((double)p_270478_, (double)p_270621_) * 57.2957763671875 - (double)this.getYRot());
        this.connection.send((Packet)new ClientboundHurtAnimationPacket((LivingEntity)this));
    }

    public boolean startRiding(Entity p_277395_, boolean p_278062_) {
        if (!super.startRiding(p_277395_, p_278062_)) {
            return false;
        }
        p_277395_.positionRider((Entity)this);
        this.connection.teleport(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
        if (p_277395_ instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)p_277395_;
            for (MobEffectInstance mobeffectinstance : livingentity.getActiveEffects()) {
                this.connection.send((Packet)new ClientboundUpdateMobEffectPacket(p_277395_.getId(), mobeffectinstance));
            }
        }
        return true;
    }

    public void stopRiding() {
        Entity entity = this.getVehicle();
        super.stopRiding();
        if (entity instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)entity;
            for (MobEffectInstance mobeffectinstance : livingentity.getActiveEffects()) {
                this.connection.send((Packet)new ClientboundRemoveMobEffectPacket(entity.getId(), mobeffectinstance.getEffect()));
            }
        }
    }

    private static /* synthetic */ Style lambda$die$4(Component p_143419_, Style p_143420_) {
        return p_143420_.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)p_143419_));
    }
}
