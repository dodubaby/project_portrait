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
