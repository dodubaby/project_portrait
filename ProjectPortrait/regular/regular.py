# encoding: utf-8
# !/usr/bin/python3

import os
import re
import sys

import util.timeUtil as timeUtil


def demo():
    key = r"AAAAhello worldBBBB"  # 这段是你要匹配的文本
    p1 = r"(?<=AAAA).+?(?=BBBB)"  # 这是我们写的正则表达式规则
    pattern1 = re.compile(p1)  # 我们在编译这段正则表达式
    matcher1 = re.search(pattern1, key)  # 在源文本中搜索符合正则表达式的部分
    print matcher1.group(0)



"""
匹配所有rule的正则
"""
def regularWord(content,regularRule):
    pattern = re.compile(regularRule)
    matcher = re.search(pattern, content)
    if (None == matcher):
        return ""
    return matcher.group(0)

"""
正则
"""


def regular(key, keyLeft, keyRight):
    p1 = r"(?<=" \
         + keyLeft \
         + r").+?(?=" \
         + keyRight \
         + r")"  # 这是我们写的正则表达式规则
    pattern1 = re.compile(p1)  # 我们在编译这段正则表达式
    matcher1 = re.search(pattern1, key)  # 在源文本中搜索符合正则表达式的部分
    if (None == matcher1):
        return ""
    return matcher1.group(0)


"""
正则
"""


def regularWithLeft(key, keyLeft):
    p1 = r"(?<=" + keyLeft + ").+"  # 这是我们写的正则表达式规则
    pattern1 = re.compile(p1)  # 我们在编译这段正则表达式
    matcher1 = re.search(pattern1, key)  # 在源文本中搜索符合正则表达式的部分
    if (None == matcher1):
        return ""
    return matcher1.group(0)


"""
正则
"""


def regularWithRight(key, keyRight):
    p1 = r".+?(?=" + keyRight + ")"  # 这是我们写的正则表达式规则
    pattern1 = re.compile(p1)  # 我们在编译这段正则表达式
    matcher1 = re.search(pattern1, key)  # 在源文本中搜索符合正则表达式的部分
    if (None == matcher1):
        return ""
    return matcher1.group(0)


"""
正则-文件-Java
"""


def regularFileJava(data):
    result = regular(data, 'src/homelink/java/', '.java')
    if ("" != result):
        return result
    result = regular(data, 'src/beike/java/', '.java')
    if ("" != result):
        return result
    result = regular(data, 'src/main/java/', '.java')
    if ("" != result):
        return result
    return result


"""
正则-资源-color
"""


def regularResourceColor(data):
    resource = {}
    resource["key"] = regular(data, '<color name="', '">')
    resource["value"] = regular(data, '>', '</color>')
    return resource


"""
正则-资源-string
"""


def regularResourceString(data):
    resource = {}
    resource["key"] = regular(data, '<string name="', '">')
    resource["value"] = regular(data, '>', '</string>')
    return resource


"""
正则-资源-dimen
"""


def regularResourceDimen(data):
    resource = {}
    resource["key"] = regular(data, '<dimen name="', '">')
    resource["value"] = regular(data, '>', '</dimen>')
    return resource


"""
test
"""


def test():
    print
    # print regular("AAAAhello worldBBBB", "AAAA", "BBBB")
    # # print regularResourceColor('<color name="color_message_user_name">#3072F6</color>')
    # # print regular("R.color.aaa)", r"R.", r"\)")
    #
    # key = r"AAAAhello worldBBBB"
    # print regularWithLeft(key, "AAAA")
    # print regularWithRight(key, "BBBB")
    #


test()
