<template>
  <div style="padding:30px; overflow: scroll">
    <el-alert :closable="false" type="info"
              title="Function说明"
              description="商机检测"
              style="margin-bottom: 20px"/>
    <el-card style="width: 1000px">
      <div slot="header" class="clearfix">
        <span>商机统计</span>
      </div>
      <div class="text item">
        <a>目前功能期望商机</a>
        <a class="textBig danger">46</a>
        <a>处，</a>
        <a class="textBig danger">2</a>
        <a>种类型，</a>
        <a>涉及功能页面</a>
        <a class="textBig danger">12</a>
        <a>个</a>
      </div>
      <div class="text item">
        <el-button>查看列表清单及参数详情</el-button>
      </div>
    </el-card>
    <el-card style="width: 1000px;margin-top: 20px">
      <div slot="header" class="clearfix">
        <span>测试过程</span>
      </div>
      <div class="text item">
        <el-button>ProjectPortraitConsole-系统控制台、数据展示门户</el-button>
        <el-form-item label="Tag-Owner">
          <el-checkbox-group v-model="checkedList4Owner" size="mini">
            <el-checkbox-button v-for="data in dataList4Owner" :label="data.value" :key="data.type"></el-checkbox-button>
          </el-checkbox-group>
        </el-form-item>
      </div>
    </el-card>
    <el-row :gutter="10">
      <el-col>
        <el-card shadow="always" header="户型详情页">
          <ul>
            <li>电话商机xxx<a class="textBig danger">1</a></li>
            <li>IM商机xxx<a class="textBig danger">2</a></li>
          </ul>
        </el-card>
      </el-col>
      <el-col>
        <el-card shadow="always" header="楼盘详情页">
          <ul>
            <li>电话商机xxx<a class="textBig danger">1</a></li>
            <li>电话商机xxx<a class="textBig danger">1</a></li>
            <el-button type="success" icon="el-icon-check" circle></el-button>
            <li>IM商机xxx<a class="textBig danger">2</a></li>
            <li>IM商机xxx<a class="textBig danger">2</a></li>
            <li>IM商机xxx<a class="textBig danger">2</a></li>
          </ul>
        </el-card>
      </el-col>
      <el-col>
        <el-card shadow="always" header="楼盘列表页面">
          <ul>
            <li>电话商机xxx<a class="textBig danger">1</a></li>
            <li>电话商机xxx<a class="textBig danger">1</a></li>
            <li>电话商机xxx<a class="textBig danger">1</a></li>
            <li>IM商机xxx<a class="textBig danger">2</a></li>
          </ul>
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
    width: 20%;
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

  .textBig {
    margin: 5px;
    font-weight: bold;
    font-size: 35px;
  }

  .textBig.success {
    color: #67C23A;
  }

  .textBig.warning {
    color: #E6A23C;
  }

  .textBig.danger {
    color: #F56C6C;
  }

  .textBig.info {
    color: #303133;
  }

</style>
