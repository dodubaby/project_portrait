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
      <el-table-column label="ID" width="90" align="center">
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
      <el-table-column class-name="status-col" label="tag" width="70" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">tag</el-tag>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button size="small" @click="buttonClick(scope.row.name)">查找引用关系</el-button>
        </template>
      </el-table-column>
   </el-table>
  </div>
</template>

<script>
import {fileFindBySuffixOrderByLineCount} from '@/api/ppserver'
import ElButton from "../../../node_modules/element-ui/packages/button/src/button";

export default {
  components: {ElButton}, filters: {
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
      fileFindBySuffixOrderByLineCount(this.listQuery).then(response => {
        this.list = response.fileList
        this.loading = false
      })
    },
    buttonClick(data){
      alert("查询【" + data + "】的所有引用关系")
    }
  }
}
</script>
