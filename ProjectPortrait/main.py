# encoding: utf-8
#!/usr/bin/python3

print("Hello,Python1")
print("Hello,Python2")

# 注释
import keyword

print("Python关键字")
print(keyword.kwlist)  # 注释

"""
多行注释
"""

'''
多行注释
'''

if True:
    print("True")
if False:
    print("False")

str = "aaaaaaaaaa " \
      + "bbbbbbbbbb " \
      + "cccccccccc " \
      + "ddddddddddd "


total = {"a", "b", "c", "d"}

print(str)
print(total)

a = 1
print(a)

a = True
print(a)

a = 3.3
print(a)

b = 1 + 2 * a
print(b)

a = "this " "is " "a " "string"
print(a)

a = "this\n" "huanhang"
print(a)

a = r"this \n 不换行"
print(a)

a = "我只显示一遍 " * 3
print(a)

a = """这是一个段落，
1可以由多行组成
2可以由多行组成
3可以由多行组成"""
print(a)

a = "12345678"
print("截取a的结果：" + a[0])
print("截取a的结果：" + a[0:-2])
print("截取a的结果：" + a[1:])

a = ''
b = ''

def test(a):
    print("方法内：" + a)
    return a * 3


print("方法返回：" + test("我是参数"))


print('我是第二行')
print('我是第三行')
