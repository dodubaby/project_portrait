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
      stripe
      highlight-current-row>
      <el-table-column label="ID" width="90" align="center" >
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="标签" width="300" align="center">
        <template slot-scope="scope">
          {{ scope.row.value }}
        </template>
      </el-table-column>
      <el-table-column label="类型" width="100" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.type }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button size="small" @click="buttonClick(scope.row.value)">查询标签对应文件</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {tagFindByType} from '@/api/ppserver'

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
      tagFindByType('common').then(response => {
        this.list = response.tagList
        this.loading = false
      })
    },
    buttonClick(data){
      alert("查询【" + data + "】标签对应文件")
    }
  }
}
</script>
