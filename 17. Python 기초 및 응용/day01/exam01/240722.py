# def add(a, b):
#     return a + b
#
# result = add(10, 20)
# print(result)

# def minus(a, b):
#     return a - b
#
# result = minus(10, 20)
# print(result)
#
# result2 = minus(b=10, a=20)
# print(result2)

# def add(a, b = 20): # b가 20으로 기본값 설정
#     return a + b
#
# result3 = add(10) # b = 20
# print(result3)

# def calc(opr, *args):
#     result = 0
#     if opr == 'add':
#         for num in args:
#             result += num
#
#     elif opr == 'minus':
#         for num in args:
#             result -= num
#
#     elif opr == 'mul':
#         result = 1
#         for num in args:
#             result *= num
#
#     return result
#
# result1 = calc('add', 10, 20, 30, 40, 50)
# print(result1)
#
# result2 = calc('mul', 10, 20, 30, 40, 50)
# print(result2)

def allkeywords(*args, **kwargs):
    print(args)
    print(kwargs)

result = allkeywords(10, 20, 30, name="이이름", age=40)
print(result)

