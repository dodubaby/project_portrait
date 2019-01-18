# encoding: utf-8
# !/usr/bin/python3

from db.dbOperate import *
from db.db import *
from portrait.analysisFileContent import *
from portrait.analysisFileList import *
from portrait.analysisResourceList import *
import util.timeUtil as timeUtil

"""
main
"""


def main():
    time = timeUtil.timeStart()

    # 数据库连接
    connect()

    # # 数据库表初始化
    # createDatabases()
    # createTables()
    # # 初始化基础数据
    # initBaseData()

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
