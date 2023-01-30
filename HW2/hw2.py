class mygraph:
    def __init__(self, numNode):
        self.adjList = {}
        self.todo = []
        self.traversal = []
        self.numNode = numNode

    def addNode(self, inputRow):
        inputList = inputRow.split()
        self.adjList[inputList[0]] = inputList[1:]
        
    def DFS(self):
        keys = list(self.adjList.keys()) 
        
        NodesNotStepped = [node for node in keys if not node in self.traversal]  

        while (len(NodesNotStepped) > 0):
            self.todo.append(NodesNotStepped[0])
            
            while len(self.todo) > 0:
                currentNode = self.todo.pop()  
                self.traversal.append(currentNode) 
            
                for adjNode in reversed(self.adjList[currentNode]):  
                    if not adjNode in self.traversal:  
                        self.todo.append(adjNode)
                        
                todo = []
                for node in reversed(self.todo):
                    if node not in todo:
                        todo.append(node)
                self.todo = [i for i in reversed(todo)]
                        
            NodesNotStepped = [node for node in keys if not node in self.traversal]  

                    
                    
                    
def main():
    numInstance = int(input()) 
    insList = []
    for i in range(numInstance):
        numNode = int(input())

        g = mygraph(numNode)
        nodeIdx = 0 
        while True:
            g.addNode(input())
            nodeIdx += 1
            if nodeIdx >= numNode:
                break
        insList.append(g)
        
    for instance in insList:
        instance.DFS()
        print(' '.join(instance.traversal))
    
insList = main()