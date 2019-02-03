#!/usr/bin/python3
# coding=utf-8

# db通用连接，统一连接与断开
dbconnect = ''

# 扫描时间（扫描行为的时间标识，用于区分新旧数据）
scanTime = ''

# 是否全量分析
# 全量分析：清理之前扫描标注的"新数据"标识，如file表中is_new标识
# 增量分析：只新增新数据，更新旧数据，保留之前标注的标识
isFullAnalysis = True