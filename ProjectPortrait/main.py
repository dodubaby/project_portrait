# encoding: utf-8
# !/usr/bin/python3

import datetime

import util.timeUtil as timeUtil
from db.db import *
from db.dbOperate import *
from portrait.analysisFileContent import *
from portrait.analysisFileList import *
from portrait.analysisResourceList import *

"""
main
"""


def main():
    time = timeUtil.timeStart()

    # 初始化扫描时间
    const.scanTime = str(datetime.datetime.now())
    # 是否全量分析
    const.isFullAnalysis = True

    # 数据库连接
    connect()

    # 数据库表初始化（系统初始化过程才执行此步骤）
    createDatabases()
    createTables()
    # 初始化基础数据（系统初始化过程才执行此步骤）
    initBaseData()

    # 清空recourse表数据
    clearTableData(TABLE_NAME_RESOURCE)
    clearTableData(TABLE_NAME_REFERENCE)

    # 分析文件列表
    analysisFileList("../../lianjia_android_nh_plugin")

    # 分析资源列表
    analysisResourceList()

    # 分析文件内容
    analysisAllFileContent()

    # 数据库断开
    disconnect()

    timeUtil.timeEnd(time)


main()
