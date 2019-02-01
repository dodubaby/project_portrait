<template>
  <div class="app-container">
    <el-alert :closable="false" type="info"
              title="Function说明"
              description="xxxxxxx"
              style="margin-bottom: 20px"
              v-loading="loading"
    />
    <el-table
      :data="list"
      border
      fit
      height="2000"
      stripe
      highlight-current-row>
      <el-table-column label="ID" width="90" align="center">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="资源value" width="400" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.resourceValue }}</span>
        </template>
      </el-table-column>
      <el-table-column label="声明数量" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.count }}
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="状态" width="100">
        <template slot-scope="scope">
          <i class="el-icon-circle-check-outline"/>
          <span>{{ scope.row.display_time }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="tag" width="70" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">tag</el-tag>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button size="small" plain @click="buttonClick(scope.row.resourceValue)">查找资源详情</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {resourceFindStatisticsByCount} from '@/api/ppserver'

export default {
  filters: {
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
      loading: false
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.loading = true
      resourceFindStatisticsByCount(this.listQuery).then(response => {
        this.list = response.resourceDataStatisticsList
        this.loading = false
      })
    },
    buttonClick(data){
      alert("查询【" + data + "】资源详情")
    }
  }
}
</script>
