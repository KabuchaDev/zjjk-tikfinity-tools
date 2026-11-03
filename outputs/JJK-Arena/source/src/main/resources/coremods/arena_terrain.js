function initializeCoreMod() {
    var ASMAPI=Java.type('net.minecraftforge.coremod.api.ASMAPI');
    var Opcodes=Java.type('org.objectweb.asm.Opcodes');
    var InsnList=Java.type('org.objectweb.asm.tree.InsnList');
    var VarInsnNode=Java.type('org.objectweb.asm.tree.VarInsnNode');
    var MethodInsnNode=Java.type('org.objectweb.asm.tree.MethodInsnNode');
    var JumpInsnNode=Java.type('org.objectweb.asm.tree.JumpInsnNode');
    var LabelNode=Java.type('org.objectweb.asm.tree.LabelNode');
    var InsnNode=Java.type('org.objectweb.asm.tree.InsnNode');
    function guard(cls,name,desc,hook,hookDesc,withState,nullable) {
        return {target:{type:'METHOD',class:cls,methodName:ASMAPI.mapMethod(name),methodDesc:desc},transformer:function(method){
            var code=new InsnList();var pass=new LabelNode();
            code.add(new VarInsnNode(Opcodes.ALOAD,0));code.add(new VarInsnNode(Opcodes.ALOAD,1));
            if(withState)code.add(new VarInsnNode(Opcodes.ALOAD,2));
            code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,'vn/local/jjkarena/ArenaTerrain',hook,hookDesc,false));
            code.add(new JumpInsnNode(Opcodes.IFEQ,pass));
            code.add(new InsnNode(nullable?Opcodes.ACONST_NULL:Opcodes.ICONST_0));
            code.add(new InsnNode(nullable?Opcodes.ARETURN:Opcodes.IRETURN));code.add(pass);
            method.instructions.insert(code);return method;
        }};
    }
    var pos='Lnet/minecraft/core/BlockPos;',state='Lnet/minecraft/world/level/block/state/BlockState;',level='Lnet/minecraft/world/level/Level;';
    return {
      level:guard('net.minecraft.world.level.Level','m_6933_','('+pos+state+'II)Z','deny','('+level+pos+state+')Z',true,false),
      chunk:guard('net.minecraft.world.level.chunk.LevelChunk','m_6978_','('+pos+state+'Z)'+state,'denyChunk','(Lnet/minecraft/world/level/chunk/LevelChunk;'+pos+state+')Z',true,true),
      destroy:guard('net.minecraft.world.level.Level','m_7740_','('+pos+'ZLnet/minecraft/world/entity/Entity;I)Z','denyDestroy','('+level+pos+')Z',false,false)
    };
}
