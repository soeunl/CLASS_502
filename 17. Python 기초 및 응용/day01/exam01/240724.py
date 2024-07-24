import time

def elapse(func):
    def wrapper(*args, **kwargs):

        start = time.time()

        result = func(*args, **kwargs) #핵심기능

        end = time.time()

        print("걸린 시간: %f" %(end-start))
        return result

    return wrapper

@elapse
def myfunc():
    print("실행!")

decoratedMyFunc = elapse(myfunc)
decoratedMyFunc()

a=input()
print(a)