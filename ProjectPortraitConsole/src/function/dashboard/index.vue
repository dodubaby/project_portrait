<template>
  <div style="padding:30px;">
    <el-carousel :interval="3000" type="card" indicatorPosition="none" height="150px">
      <el-carousel-item style="align-content: center" v-loading="loading">
        <h1>Welcome to Project Portrait</h1>
      </el-carousel-item>
      <el-carousel-item>
        <h1>欢迎来到 项目画像（分析）系统</h1>
      </el-carousel-item>
      <el-carousel-item>
        <h1>Welcome to Project Portrait</h1>
      </el-carousel-item>
      <el-carousel-item>
        <h1>欢迎来到 项目画像（分析）系统</h1>
      </el-carousel-item>
    </el-carousel>

    <el-row :gutter="10" style="margin-top: 30px">
      <el-col>
        <el-card shadow="always" header="崩溃预防">
          <ul>
            <li>检测到极可能被触发的<b>CRASH</b><a class="text danger">{{ badRuleAnalysis.errorCount }}</a>处</li>
            <li>检测到代码逻辑<b>告警点</b><a class="text danger">{{ badRuleAnalysis.warnCount }}</a>处</li>
          </ul>
        </el-card>
      </el-col>
      <el-col>
        <el-card shadow="always" header="优化/质量筛查">
          <li>检测到重复定义了<a class="text warning">{{qualityaAnalysis.stringReplicationCount}}</a>个<b>字符串资源</b></li>
          <li>检测到重复定义了<a class="text warning">{{qualityaAnalysis.colorReplicationCount}}</a>个<b>颜色值资源</b></li>
          <li>检测到共有<a class="text warning">{{qualityaAnalysis.fileLongCount}}</a>个文件<b>行数超过500行</b>，需要重构</li>
          <li>检测到共有<a class="text warning">{{qualityaAnalysis.fileLongCount}}</a>个文件<b>体积较大</b>，需要重构</li>
          <li>检测到代码和资源中共有<a class="text warning">{{badRuleAnalysis.hardcodeCount}}</a>处<b>硬编码</b>，潜在风险</li>
        </el-card>
      </el-col>
      <el-col>
        <el-card shadow="always" header="功能管理、人效管理">
          <li>目前共有<a class="text info">{{functionAnalysis.functionNumbers}}</a>位<b>参与及贡献者</b></li>
          <li>累计完成<a class="text success">{{functionAnalysis.coreFunctionCounts}}</a>个<b>核心业务功能点</b></li>
          <li>设计并实现了<a class="text success">{{functionAnalysis.commonFunctionCounts}}</a>个<b>通用能力模块</b></li>
        </el-card>
      </el-col>
      <el-col>
        <el-card shadow="always" header="业务赋能">
          <li>如果你想<b>持续保证商机准确性</b>，猛戳这里<a class="text success" style="font-size: 18px">商机检测</a></li>
          <li>如果你想<b>全面回归埋点数据</b>，猛戳这里<a class="text success" style="font-size: 18px">埋点检测</a></li>
        </el-card>
      </el-col>
      <el-col>
        <el-card shadow="always" header="增强工具">
          <li>如果你想<b>评估风险范围</b>亦或<b>查询引用关系</b>，欢迎进行<a class="text success" style="font-size: 18px">逻辑关系查询</a></li>
          <li>如果你要检查<b>代码层级结构</b>情况如何，可以尝试<a class="text success" style="font-size: 18px">层级关系查询</a></li>
          <li>如果你有需要，进行IDE无法支持的<b>多维度检索</b>，猛敲这里<a class="text success" style="font-size: 18px">多维度检索</a></li>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {statisBoard} from '@/api/ppserver'

export default {
  data() {
    return {
      badRuleAnalysis: '',
      showReferenceRelationship: '',
      qualityaAnalysis: '',
      functionAnalysis: ''
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.loading = true
      statisBoard().then(response => {
        this.badRuleAnalysis = response.badRuleAnalysis
        this.showReferenceRelationship = response.showReferenceRelationship
        this.qualityaAnalysis = response.qualityaAnalysis
        this.functionAnalysis = response.functionAnalysis
        this.loading = false
      })
    }
  }
}
</script>

<style>
  .el-carousel__item h1 {
    color: #475669;
    font-size: 25px;
    align-content: center;
    align-items: center;
    align-self: center;
    opacity: 0.75;
    line-height: 120px;
    margin: 20px;
  }

  .el-col {
    width: 48%;
    margin: 1%;
  }

  .el-col li {
    font-size: 16px;
    line-height: 33px;
    color: #606266;
  }

  .el-col li b {
    font-size: 16px;
    color: #303133;
    margin: 5px;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  .text {
    margin: 5px;
    font-weight: bold;
    font-size: 28px;
  }

  .text.success {
    color: #67C23A;
  }

  .text.warning {
    color: #E6A23C;
  }

  .text.danger {
    color: #F56C6C;
  }

  .text.info {
    color: #303133;
  }

</style>
