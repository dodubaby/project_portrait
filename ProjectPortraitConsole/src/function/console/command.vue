<template>
  <div style="padding:30px;">
    <el-alert :closable="false" title="【控制台】全局命令" type="info"/>
    <div style="padding:20px;  ">
      <el-button size="big" @click="buttonClick()" style="width: 415px">批量执行所有</el-button>
    </div>
    <el-alert :closable="false" title="【控制台】系统命令" type="info"/>
    <div style="padding:20px;  ">
      <el-button size="big" @click="buttonClick()" style="width: 200px">数据库表初始化</el-button>
      <el-button size="big" @click="buttonClick()" style="width: 200px">初始化基础数据</el-button>
    </div>
    <el-alert :closable="false" title="【控制台】独立命令" type="info"/>
    <div style="padding:20px;  ">
      <el-button size="big" @click="buttonClick()" style="width: 200px">分析文件列表</el-button>
      <el-button size="big" @click="buttonClick()" style="width: 200px">分析资源列表</el-button>
      <el-button size="big" @click="buttonClick()" style="width: 200px">分析文件内容</el-button>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    buttonClick(){
      alert("脚本命令尚未对接，请暂时手动启用命令，敬请期待~")
    }
  }
}
</script>
