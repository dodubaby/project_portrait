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
    <el-tag>Tag-Common</el-tag>
    <span>max=1</span>
    <el-checkbox-group
      v-model="checkedList4Common"
      :min="0"
      :max="1"
      size="mini"
      @change="handleChange">
      <el-checkbox-button v-for="data in dataList4Common" :label="data.value" :key="data.type"></el-checkbox-button>
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
    <el-button type="primary" @click="close">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import {tagDataFindByDataId, tagDataUpdateTags} from '@/api/ppserver'
import ElCheckboxButton from "../../../node_modules/element-ui/packages/checkbox/src/checkbox-button";

export default {
  components: {ElCheckboxButton},
  name: 'tagManage',
  props: ['data','dataId', 'dataType'],
  data () {
    return {
      dialogVisible: true,
      dataList4Owner: [],
      dataList4Function: [],
      dataList4Common: [],
      dataList4Other: [],
      checkedList4Owner: [],
      checkedList4Function: [],
      checkedList4Common: [],
      checkedList4Other: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.loading = true
      tagDataFindByDataId(this.dataType, this.dataId).then(response => {
        this.dataList4Owner = response.tagList4Owner
        this.dataList4Function = response.tagList4Function
        this.dataList4Common = response.tagList4Common
        this.dataList4Other = response.tagList4Other
        this.checkedList4Owner = response.tagValueList4Owner
        this.checkedList4Function = response.tagValueList4Function
        this.checkedList4Common = response.tagValueList4Common
        this.checkedList4Other = response.tagValueList4Other
        this.loading = false
      })
    },
    close() {
      this.$emit('callBack')
    },
    handleClose(done) {
      done();
      this.$emit('callBack')
    },
    handleChange(value){
      this.loading = true
      var tags = this.checkedList4Owner + "," + this.checkedList4Function + "," + this.checkedList4Common + "," + this.checkedList4Other
      tagDataUpdateTags(this.dataType, this.dataId, tags).then(response => {
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
    margin-bottom: 20px;
  }

  .el-checkbox-button {
    margin-bottom: 5px;
  }
</style>
