import math
from collections import Counter
from itertools import combinations
from queue import PriorityQueue
import heapq

def li(): return list(map(int,input().split()))

def nt(): return int(input())

def st(): return input()

#------------------------------------------------------------------------

yes="YES"
no="NO"
even="EVEN"
odd="ODD"
mod=998244353

#------------------------------------------------------------------------
            

def prefix(L):
    size=len(L)
    for i in range(size-1):
        L[i+1]=L[i]+L[i+1]
    return L

def exponentiation(bas, exp, mod):
    t = 1
    while(exp > 0): 
  
        if (exp % 2 != 0):
            t = (t * bas) % mod
  
        bas = (bas * bas) % mod 
        exp //= 2
    return t % mod

def binlist(x):
    L=[0]*21
    temp=bin(x)
    temp=temp[2:]
    x=len(temp)
    indexL=[]
    for i in range(x-1,-1,-1):
        if temp[i]=='1':
            L[x-i]=1
            indexL.append(x-i)
    return L
    
def binlisttoint(L):
    x=0
    for i in range(1,21):
        if L[i]=='1':
            x+=2**(i-1)
    return x
#------------------------------------------------------------------------


for _ in range(int(input())):
    n=nt()
    L=li()
    a=L[0]
    j=L[-1]
    susL=[]
    susL2=[]
    x=0
    y=0
    for i in range(n-2):
        if (a&L[i]<a and x|L[i]>x):
            susL.append(L[i])
            continue
        a&=L[i]
        x|=L[i]
    for i in range(2,n):
        if(j&L[i]<j and y|L[i]>y):
            susL2.append(L[i])
            continue
        j&=L[i]
        y|=L[i]
    for i in susL:
        temp=a&i
        temp2=x|i
        check=a-temp
        check2=temp2-x
        if check>=check2:
            a&=i
        else:
            x|=i
    for i in susL2:
        temp=j&i
        temp2=y|i
        check=j-temp
        check2=temp2-y
        if check>=check2:
            j&=i
        else:
            y|=i
    print(max(x-a,y-j))90