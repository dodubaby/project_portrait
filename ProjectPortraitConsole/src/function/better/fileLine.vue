<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column label="索引" width="90" align="center" >
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="行数（行）" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.lineCount }}
        </template>
      </el-table-column>
      <el-table-column label="大小（KB）" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.size }}
        </template>
      </el-table-column>
      <el-table-column label="文件名" width="300" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="后缀" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.suffix }}
        </template>
      </el-table-column>
      <el-table-column label="全路径" align="left">
        <template slot-scope="scope">
          {{ scope.row.classFullName }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
   </el-table>
  </div>
</template>

<script>
import {fileFindBySuffixOrderByLineCount} from '@/api/ppserver'

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
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      fileFindBySuffixOrderByLineCount(this.listQuery).then(response => {
        this.list = response.fileList
        this.listLoading = false
      })
    }
  }
}
</script>
