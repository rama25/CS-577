class CountInversion:
    def __init__(self, List):
        self.listInput = List
    
    def mergeCount(self, l1, l2):
        mergeList = []
        idx1 = 0
        idx2 = 0
        count = 0
        while True:
            if len(l1) == idx1 and len(l2) == idx2: 
                return mergeList, count

            add2 = False   
            if len(l1) == idx1: 
                add2 = True
            elif  len(l2) > idx2 and l1[idx1] > l2[idx2]:
                add2 = True
                
            if(add2):
                mergeList.append(l2[idx2])
                count += len(l1) - idx1
                idx2 += 1
            else:
                mergeList.append(l1[idx1])
                idx1 += 1
    
    def countSort(self, L):
        if len(L) == 1:
            return L, 0
        mid = len(L)//2

        l1, c1 = self.countSort(L[:mid])
        l2, c2 = self.countSort(L[mid:])

        sortL, c = self.mergeCount(l1, l2)

        return sortL, c1 + c2 + c
    
    def getCount(self):
        _, count = self.countSort(self.listInput)
        return count
    
class ReorderList:
    def mergeOrder(self, l1, l2, r1, r2): 
        mergeListTop = []
        mergeListBot = []
        idx1 = 0
        idx2 = 0
        while True:
            if len(l1) == idx1 and len(l2) == idx2: 
                return mergeListTop, mergeListBot

            add2 = False   
            if len(l1) == idx1: 
                add2 = True
            elif  len(l2) > idx2 and l1[idx1] > l2[idx2]:
                add2 = True
                
            if(add2):
                mergeListTop.append(l2[idx2])
                mergeListBot.append(r2[idx2])
                
                idx2 += 1
            else:
                mergeListTop.append(l1[idx1])
                mergeListBot.append(r1[idx1])
                idx1 += 1
    def reOrder(self, L, R):
        if len(L) == 1:
            return L, R
        mid = len(L)//2

        l1, r1 = self.reOrder(L[:mid], R[:mid])
        l2, r2 = self.reOrder(L[mid:], R[mid:])

        l, r = self.mergeOrder(l1, l2, r1, r2)

        return l, r
    
    def getOrder(self):
        l, r = self.reOrder(self.L, self.R)
        return l, r
    

def main():
    Order = ReorderList() 
    numInstance = int(input())  
    countList = []
    for i in range(numInstance):
        numNode = int(input())  
        L = []  
        R = []  
        for node in range(numNode):
            L.append(int(input()))
        for node in range(numNode):
            R.append(int(input()))
        
        _,r = Order.reOrder(L,R)  
        count = CountInversion(r)
        countList.append(count.getCount())
                
        
    for count in countList:
        print(count)
        
main()