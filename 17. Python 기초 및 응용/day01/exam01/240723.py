# def anyValue(*args, **kwargs):
#     print(args)
#     print(**kwargs)

# a = 10
# def add(a):
#     a += 1
#
# add(a)
# print(a)

# nums = [i for i in range(1, 11)]
# print(nums)
#
# def square(num):
#     return num * num
#
# num2 = list(map(square, nums))
# print(num2)

# def add(num1):
#     def add2(num2):
#         return num1 + num2
#     return add2
#
# add10 = add(10)
# result = add10(20)
# print(result)

def elapsed(func):
    def wrapper():
        # 공통 처리 부분
        result1 = func() # 핵심 기능
        # 공통 처리 부분 
        return result1
    
    return wrapper()

def factorial(num):
    if num < 1: return  1

    return num * factorial(num - 1)

result = factorial(10)
print(result)
