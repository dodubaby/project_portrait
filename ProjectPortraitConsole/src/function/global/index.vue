<template>
  <div style="padding:30px;">
    <el-alert :closable="false" type="info"
              title="视图说明"
              description="项目熟悉、风险范围评估、优先级评估"/>
    <tag-manage data="Text" dataId="TextId" @callBack="dataUpdate"></tag-manage>
    <el-tag>{{checkData}}</el-tag>
  </div>
</template>

<script>
import TagManage from '../../components/function/tagManage'
import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
import ElTag from "../../../node_modules/element-ui/packages/tag/src/tag";
export default {
  components: {
    ElTag, ElButton,
    'name': 'index',
    'tag-manage': TagManage
  },
  data () {
    return {
      checkData: 'default'
    }
  },
  methods: {
    dataUpdate () {
      this.checkData = '选中了'
    }
  }
}
</script>
