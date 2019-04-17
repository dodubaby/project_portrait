<template>
  <div style="padding:30px; overflow: scroll">
    <el-alert :closable="false" type="info"
              title="Function说明"
              description="商机检测"
              style="margin-bottom: 20px"/>
    <el-card style="width: 100%">
      <div slot="header" class="clearfix">
        <span>商机统计</span>
      </div>
      <div class="text item">
        <a>目前功能期望商机</a>
        <a class="textBig success">46</a>
        <a>处，</a>
        <a class="textBig success">2</a>
        <a>种类型，</a>
        <a>涉及功能页面</a>
        <a class="textBig success">12</a>
        <a>个</a>
      </div>
      <div class="text item" style="margin-top: 10px">
        <el-button>查看列表清单及参数详情</el-button>
      </div>
    </el-card>
    <el-card style="width: 100%;margin-top: 20px">
      <div slot="header" class="clearfix">
        <span>测试过程</span>
      </div>
      <div class="text item">
        <el-form label-width="120px">
          <el-form-item label="测试设备">
            <el-radio-group v-model="radio1">
              <el-radio-button label="15810592135"></el-radio-button>
              <el-radio-button label="HTC 2Q4D200"></el-radio-button>
              <el-radio-button label="IPhone6"></el-radio-button>
              <el-radio-button label="IPhone8"></el-radio-button>
              <el-radio-button label="GIONEE GN5007	"></el-radio-button>
              <el-radio-button label="GN5001S"></el-radio-button>
              <el-radio-button label="Redmi 5 Plus	"></el-radio-button>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="数据清理">
            <el-button plain @click="onReset">清空当前设备的测试数据</el-button>
          </el-form-item>
          <el-form-item label="测试操作">
            <el-button type="primary" plain @click="getHierarchy">开始测试</el-button>
            <el-button type="primary" plain @click="getHierarchy">暂停测试</el-button>
          </el-form-item>
          <el-form-item label="测试统计">
            <el-button size="mini" plain @click="onReset">上次自动更新：2019/4/17 12:19:47（点击立刻更新）</el-button>
            <div class="text item">
              <a>测试进度：</a>
              <a class="textBig info">37%</a>
              <a>完全命中：</a>
              <a class="textBig success">12</a>
              <a>错误：</a>
              <a class="textBig danger">2</a>
              <a>重复：</a>
              <a class="textBig danger">1</a>
              <a>计划外：</a>
              <a class="textBig danger">1</a>
            </div>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card style="width: 100%;margin-top: 20px">
      <div slot="header" class="clearfix">
        <span>测试详情</span>
      </div>
      <el-button size="mini" plain @click="onReset">上次自动更新：2019/4/17 12:19:47（点击立刻更新）</el-button>
      <el-row :gutter="10">
        <el-col>
          <el-card shadow="always" header="【最新数据上报】">
            <li><a>最新的数据上报报文</a></li>
            <li><a>最新的数据上报报文</a></li>
            <li><a>最新的数据上报报文</a></li>
            <li><a>最新的数据上报报文</a></li>
            <li><a>最新的数据上报报文</a></li>
            <li><a>最新的数据上报报文</a></li>
          </el-card>
        </el-col>
        <el-col>
          <el-card shadow="always" header="户型详情页">
            <ul>
              <li><a class="info">户型详情页-咨询首付贷款_guwen</a></li>
              <li>
                <el-badge value=">New<" class="item">
                  <a class="success">户型详情页-咨询位置周边情况_ssc</a></el-badge>
              </li>
              <li><a class="success">户型详情页-咨询首付贷款_xwx</a></li>
            </ul>
          </el-card>
        </el-col>
        <el-col>
          <el-card shadow="always" header="楼盘详情页">
            <ul>
              <li><a class="info">楼盘详情页-底部bar_guwen</a></li>
              <li>
                <el-badge value="3" class="item">
                  <a class="success">楼盘详情页-底部bar_ssc</a></el-badge>
              </li>
              <li><a class="success">楼盘详情页-底部bar_xwx</a></li>
              <li><a class="info">楼盘详情页-快速提问_guwen</a></li>
              <li>
                <el-badge value="2" class="item">
                  <a class="success">楼盘详情页-快速提问_ssc</a></el-badge>
              </li>
              <li><a class="success">楼盘详情页-快速提问_xwx</a></li>
            </ul>
          </el-card>
        </el-col>
        <el-col>
          <el-card shadow="always" header="楼盘列表页面">
            <ul>
              <li><a class="info">楼盘列表页面-咨询首付贷款_guwen</a></li>
              <li>
                <el-badge value="2" class="item">
                  <a class="success">楼盘列表页面-咨询位置周边情况_ssc</a></el-badge>
              </li>
              <li><a class="success">楼盘列表页面-咨询首付贷款_xwx</a></li>
            </ul>
          </el-card>
        </el-col>
        <el-col>
          <el-card shadow="always" header="位置周边三级页">
            <ul>
              <li><a class="info">位置周边三级页-咨询首付贷款_guwen</a></li>
              <li>
                <a class="success">位置周边三级页-咨询位置周边情况_ssc</a>
              </li>
              <li>
                <a class="info">位置周边三级页-咨询位置周边情况_ssc</a>
              </li>
              <li>
                <el-badge value="2" class="item">
                  <a class="success">位置周边三级页-咨询位置周边情况_ssc</a></el-badge>
              </li>
              <li><a class="success">位置周边三级页-咨询首付贷款_xwx</a></li>
            </ul>
          </el-card>
        </el-col>
        <el-col>
          <el-card shadow="always" header="楼盘信息页">
            <ul>
              <li><a class="info">楼盘信息页-咨询首付贷款_guwen</a></li>
              <li>
                <a class="success">楼盘信息页-咨询位置周边情况_ssc</a>
              </li>
              <li>
                <a class="info">楼盘信息页-咨询位置周边情况_ssc</a>
              </li>
              <li>
                <a class="info">楼盘信息页-咨询位置周边情况_ssc</a>
              </li>
              <li>
                <a class="info">楼盘信息页-咨询位置周边情况_ssc</a>
              </li>
              <li>
                <el-badge value="2" class="item">
                  <a class="success">楼盘信息页-咨询位置周边情况_ssc</a></el-badge>
              </li>
              <li><a class="success">楼盘信息页-咨询首付贷款_xwx</a></li>
            </ul>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      radio1: '北京',
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
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
    width: 32%;
    margin: 0.5%;
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
    font-size: 28px;
  }

  .success {
    color: #67C23A;
  }

  .warning {
    color: #E6A23C;
  }

  .danger {
    color: #F56C6C;
  }

  .info {
    color: #303133;
  }

  .item {
    margin-right: 10px;
  }
</style>
