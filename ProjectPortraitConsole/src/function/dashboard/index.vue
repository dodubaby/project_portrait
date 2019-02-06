<template>
  <div style="padding:30px;">
    <el-carousel :interval="3000" type="card" indicatorPosition="none" height="250px">
      <el-carousel-item style="align-content: center">
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
      <el-col :span="7">
        <el-card shadow="always" header="项目概况 / 层级结构 / 引用关系">
          <el-tag>项目全局</el-tag>
          <el-tag>逻辑关系</el-tag>
        </el-card>
      </el-col>
      <el-col :span="7">
        <el-card shadow="always" header="功能管理 / 人效管理 / 质量把控与管理">
          <el-tag>标签</el-tag>
          <el-tag>优化/质量筛查</el-tag>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card shadow="always" header="开发工具">
          <el-tag>规则无限可能</el-tag>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card shadow="always" header="系统">
          <el-tag>控制台</el-tag>
          <el-tag>关于</el-tag>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style>
  .el-carousel__item h1 {
    color: #475669;
    font-size: 30px;
    align-content: center;
    align-items: center;
    align-self: center;
    opacity: 0.75;
    line-height: 200px;
    margin: 20px;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
