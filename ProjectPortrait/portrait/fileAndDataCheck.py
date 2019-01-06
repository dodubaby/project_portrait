#!/usr/bin/python3
# coding=utf-8

"""
忽略文件
"""


def ignoreFile(str):
    if str.startswith("."):
        return True
    if str.startswith("__"):
        return True
    if str == "build":
        return True
    if str.endswith("jar"):
        return True
    if str.endswith("zip"):
        return True
    if str.endswith("rar"):
        return True
    if str.endswith("hprof"):
        return True
    if str.endswith(".json"):
        return True
    else:
        return False


"""
忽略文件
"""


def ignoreReferenceData(str):
    if str.startswith("android."):
        return True
    if str.startswith("java."):
        return True
    else:
        return False
