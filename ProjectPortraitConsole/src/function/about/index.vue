<template>
  <div style="padding:30px;">
    <el-alert :closable="false" type="info"
              title="Project Portrait"
              description="项目画像（分析）工具"
              style="margin-bottom: 20px"
              v-loading="loading"
    />
    <el-card style="width: 1000px">
      <div slot="header" class="clearfix">
        <span>贡献者名单</span>
      </div>
      <div class="text item">
        <el-tag type="info">排名以加入项目时间为准</el-tag>
      </div>
      <div class="text item">
        <el-tooltip effect="dark" content="梁金柱：此处放这位大牛的介绍" placement="right">
          <el-tag class="tag_name">梁金柱(精灵鼠)-260161000</el-tag>
        </el-tooltip>
      </div>
      <div class="text item">
        <el-tooltip effect="dark" content="符飚：此处放这位大牛的介绍" placement="right">
          <el-tag class="tag_name">符飚(笑哈哈)-23109668</el-tag>
        </el-tooltip>
      </div>
    </el-card>
    <el-card style="width: 1000px;margin-top: 20px">
      <div slot="header" class="clearfix">
        <span>项目地址</span>
      </div>
      <div class="text item">
        <el-button onclick="window.open('https://github.com/laoshubuluo/project_portrait')">
          项目主页：https://github.com/laoshubuluo/project_portrait
        </el-button>
      </div>
      <div class="text item">
        <el-button onclick="window.open('https://github.com/laoshubuluo/project_portrait/blob/master/doc/@功能设计.md')">
          功能设计：https://github.com/laoshubuluo/project_portrait/blob/master/doc/@功能设计.md
        </el-button>
      </div>
      <div class="text item">
        <el-button onclick="window.open('https://github.com/laoshubuluo/project_portrait/tree/master/doc')">
          辅助文档：https://github.com/laoshubuluo/project_portrait/tree/master/doc
        </el-button>
      </div>
    </el-card>
    <el-card style="width: 1000px;margin-top: 20px">
      <div slot="header" class="clearfix">
        <span>系统设计</span>
      </div>
      <div class="text item">
        <el-button>ProjectPortraitScan-原始数据采集系统</el-button>
      </div>
      <div class="text item">
        <el-button>ProjectPortraitServer-数据整合系统及输出能力提供者</el-button>
      </div>
      <div class="text item">
        <el-button>ProjectPortraitConsole-系统控制台、数据展示门户</el-button>
      </div>
    </el-card>
  </div>
</template>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 25px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .tag_name {
    width: auto;
    height: auto;
    font-size: 18px;
  }
</style>
