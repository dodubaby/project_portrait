<template>
  <div class="app-container">
    <el-alert :closable="false" type="info"
              title="Rule"
              description="xxxxxxx"
              style="margin-bottom: 20px"
              v-loading="loading"
    />
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="新建规则">
        <el-input v-model="form.scanFileSuffix" placeholder="scanFileSuffix" style="width: auto;"/>
        <el-input v-model="form.keyLeft" placeholder="keyLeft" style="width: auto;"/>
        <el-input v-model="form.keyRight" placeholder="keyRight" style="width: auto;"/>
        <el-input v-model="form.ruleGroup" placeholder="ruleGroup" style="width: auto;"/>
        <el-input v-model="form.remark" placeholder="remark" style="width: auto;"/>
        <el-input v-model="form.creater" placeholder="creater" style="width: auto;"/>
      </el-form-item>
      <el-form-item style="margin-top: 10px;">
        <el-button plain @click="handleInsert()">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table style="margin-top: 30px"
              :data="list"
              border
              fit
              stripe
              highlight-current-row>
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-table
            :data="scope.row.ruleDataList"
            border
            fit
            stripe
            highlight-current-row>
            <el-table-column label="fileId" prop="fileId" width="100" align="center"></el-table-column>
            <el-table-column label="fileName" prop="fileName" width="200" align="center"></el-table-column>
            <el-table-column label="data" prop="data" width="450" align="left"></el-table-column>
            <el-table-column label="dataLine" prop="dataLine" width="100" align="center"></el-table-column>
            <el-table-column class-name="status-col" label="status" width="70" align="center">
              <template slot-scope="scope">
                <el-tag :type="scope.row.status | statusFilter"></el-tag>
              </template>
            </el-table-column>
            <el-table-column class-name="status-col" label="操作" width="100" align="center">
              <template slot-scope="scope">
                <el-button size="small" @click="buttonClick(scope.row.name)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column label="命中数量" width="100" align="center">
        <template slot-scope="scope">
          <el-tag type="danger">{{ scope.row.ruleDataListSize }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="keyLeft" prop="keyLeft" width="250" align="center"></el-table-column>
      <el-table-column label="keyRight" prop="keyRight" width="250" align="center"></el-table-column>
      <el-table-column label="ruleGroup" prop="ruleGroup" width="100" align="center"></el-table-column>
      <el-table-column label="scanFileSuffix" prop="scanFileSuffix" width="150" align="center"></el-table-column>
      <el-table-column label="remark" prop="remark" width="200" align="center"></el-table-column>
      <el-table-column label="creater" prop="creater" width="100" align="center"></el-table-column>
      <el-table-column label="createTime" prop="createTime" width="100" align="center"></el-table-column>
      <el-table-column class-name="status-col" label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" plain @click="ruleDeleteConfirm(scope.$index, scope.row.id)">
            删除


          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {ruleDataFindAll, ruleInsert, ruleDelete} from '@/api/ppserver'
import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
import ElTag from "../../../node_modules/element-ui/packages/tag/src/tag";
import ElInput from "../../../node_modules/element-ui/packages/input/src/input";

export default {
  components: {ElInput, ElTag, ElButton}, filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      loading: true,
      form: {
        scanFileSuffix: '',
        keyLeft: '',
        keyRight: '',
        ruleGroup: '',
        remark: '',
        creater: ''
      },
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.loading = true
      ruleDataFindAll('error,warn').then(response => {
        this.list = response.ruleList
        this.loading = false
      })
    },
    handleInsert() {
      this.loading = true
      ruleInsert(this.form.scanFileSuffix, this.form.keyLeft, this.form.keyRight, this.form.ruleGroup, this.form.remark, this.form.creater).then(response => {
        this.loading = false
        this.$notify({
          title: '规则新增成功',
          message: '新增规则，会在下一次扫描过程中生效',
          type: 'success'
        });
        // 刷新页面
        this.fetchData()
      })
    },
    handleDelete(index, id) {
      this.loading = true
      ruleDelete(id).then(response => {
        this.loading = false
        this.$notify({
          title: '规则删除成功',
          message: '规则删除成功',
          type: 'success'
        });
        // 刷新页面
        this.fetchData()
      })
    },
    ruleDeleteConfirm(index, id) {
      this.$confirm('确定删除此规则？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(index, id)
      }).catch(() => {
      });
    },
  }
}
</script>
