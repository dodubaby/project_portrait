<template>
  <el-dialog
    title="标签管理"
    :visible.sync="dialogVisible"
    width="40%"
    :before-close="handleClose">
    <el-alert :closable="false" type="info"
              :title="data"
              :description="dataId" v-loading="loading" style="margin-bottom: 10px"/>
    <el-tag>Tag-Owner</el-tag>
    <span>max=3</span>
    <el-checkbox-group
      v-model="checkedList4Owner"
      :min="0"
      :max="3"
      size="mini"
      @change="handleChange">
      <el-checkbox-button v-for="data in dataList4Owner" :label="data.value" :key="data.type"></el-checkbox-button>
    </el-checkbox-group>
    <el-tag>Tag-Function</el-tag>
    <span>max=2</span>
    <el-checkbox-group
      v-model="checkedList4Function"
      :min="0"
      :max="2"
      size="mini"
      @change="handleChange">
      <el-checkbox-button v-for="data in dataList4Function" :label="data.value" :key="data.type"></el-checkbox-button>
    </el-checkbox-group>
    <el-tag>Tag-Other</el-tag>
    <el-checkbox-group
      v-model="checkedList4Other"
      :min="0"
      size="mini"
      @change="handleChange">
      <el-checkbox-button v-for="data in dataList4Other" :label="data.value" :key="data.type"></el-checkbox-button>
    </el-checkbox-group>

    <span slot="footer">
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {tagDataFindByDataId, tagDataUpdateTags} from '@/api/ppserver'
import ElCheckboxButton from "../../../node_modules/element-ui/packages/checkbox/src/checkbox-button";

export default {
  components: {ElCheckboxButton},
  name: 'tagManage',
  props: ['dataId', 'data'],
  data () {
    return {
      dialogVisible: true,
      dataList4Owner: [],
      dataList4Function: [],
      dataList4Other: [],
      checkedList4Owner: [],
      checkedList4Function: [],
      checkedList4Other: [],
      dataChanged: false // 数据是否变化
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.loading = true
      tagDataFindByDataId('file', this.dataId).then(response => {
        this.dataList4Owner = response.tagList4Owner
        this.dataList4Function = response.tagList4Function
        this.dataList4Other = response.tagList4Other
        this.checkedList4Owner = response.tagValueList4Owner
        this.checkedList4Function = response.tagValueList4Function
        this.checkedList4Ohter = response.tagValueList4Other
        this.loading = false
      })
    },
    handleClose(done) {
      // 关闭前回调特定方法
      if (this.dataChanged) {
        this.$emit('callBack')
      }
      done();
    },
    handleChange(value){
      this.dataChanged = true
      this.loading = true
      var tags = this.checkedList4Owner + "," + this.checkedList4Function + "," + this.checkedList4Other
      tagDataUpdateTags('file', this.dataId, tags).then(response => {
        this.$notify({
          title: '标签更新成功',
          message: tags,
          type: 'success'
        });
        this.loading = false
      })
    }
  }
}
</script>
<style>
  .el-checkbox-group {
    margin-top: 5px;
    margin-bottom: 10px;
  }

  .el-checkbox-button {
    margin-bottom: 5px;
  }
</style>
