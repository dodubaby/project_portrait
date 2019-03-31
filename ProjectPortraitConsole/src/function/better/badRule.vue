<template>
  <div class="app-container">
    <el-alert :closable="false" type="info"
              title="Rule"
              description="xxxxxxx"
              style="margin-bottom: 20px"
              v-loading="loading"
    />
    <el-table
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
            <el-table-column label="status" prop="status" width="100" align="center"></el-table-column>
            <el-table-column class-name="status-col" label="tag" width="70" align="center">
              <template slot-scope="scope">
                <el-tag :type="scope.row.status | statusFilter">tag</el-tag>
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
      <el-table-column label="type" prop="type" width="50" align="center"></el-table-column>
      <el-table-column label="remark" prop="remark" width="200" align="center"></el-table-column>
      <el-table-column label="creater" prop="creater" width="100" align="center"></el-table-column>
      <el-table-column label="createTime" prop="createTime" width="100" align="center"></el-table-column>
      <el-table-column class-name="status-col" label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button size="small" @click="buttonClick(scope.row.name)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {ruleDataFindAll} from '@/api/ppserver'
import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
import ElTag from "../../../node_modules/element-ui/packages/tag/src/tag";

export default {
  components: {ElTag, ElButton}, filters: {
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
      loading: true
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
    buttonClick(data){
      alert("查询【" + data + "】的所有引用关系")
    }
  }
}
</script>
