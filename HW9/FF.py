class Node:
    visited = set()  
    def __init__(self, name):
        self.name = name
        self.children = []  
        self.childrenNames = {} 

    def __repr__(self):
        return "node %s" % self.name
  
    def findPath(self, dst): 
        if self.name in Node.visited:
            return []
        
        Node.visited.add(self.name)

        if self.name == dst:
            return [self.name]

        if len(self.children) == 0: 
            return []

        for child in self.children:
            path = child.findPath(dst)
            if(len(path) > 0):
                path.append(self.name)
                return path
        return []
        

class graph:
    def __init__(self):
        self.nodes = {}
    
    def addNode(self, name):
        self.nodes[name] = Node(name)

    def add_edge(self, u, v, w): 
        if w<0:
            raise ValueError("No negative edge cost! Check your input!")
            
        for name in [u,v]:
            if not name in self.nodes:
                self.addNode(name)
            
        if not self.nodes[v] in self.nodes[u].children and w > 0:
            self.nodes[u].children.append(self.nodes[v])
        

        if v in self.nodes[u].childrenNames:
            self.nodes[u].childrenNames[v] += w
        else:
            self.nodes[u].childrenNames[v] = w

    def edge(self, u, v, w):
        if w<0:
            raise ValueError("No negative edge cost! Check your input!")
            
        for name in [u,v]:
            if not name in self.nodes:
                self.addNode(name)
            
        if not self.nodes[v] in self.nodes[u].children and w > 0:
            self.nodes[u].children.append(self.nodes[v])
        
        if self.nodes[v] in self.nodes[u].children and w == 0:
            self.nodes[u].children.remove(self.nodes[v])

        self.nodes[u].childrenNames[v] = w
    

    def DFS(self, s, t):
        Node.visited = set() 
        reversedPath = self.nodes[s].findPath(t)


        return [i for i in reversed(reversedPath)]
    
    def FordFulkerson(self, s, t):
        if s==t:
            raise ValueError("Error! Source and sink is the same node!")
        maxFlow = 0
        while True: 
            path = self.DFS(s, t)
            
            if len(path) == 0:
                break

            bottleNeck = float("Inf")
            for i in range(len(path)-1):
                if self.nodes[path[i]].childrenNames[path[i+1]] < bottleNeck:
                    bottleNeck = self.nodes[path[i]].childrenNames[path[i+1]]
            if bottleNeck <= 0:
                raise ValueError("Non-posotive bottle neck! Check your computation!")
            maxFlow += bottleNeck

            for j in range(len(path)-1):
                w = self.nodes[path[j]].childrenNames[path[j+1]]
                self.edge(path[j], path[j+1], w - bottleNeck) 

                if path[j] in self.nodes[path[j+1]].childrenNames:
                    reverseFlow = bottleNeck + self.nodes[path[j+1]].childrenNames[path[j]]
                else:
                    reverseFlow = bottleNeck
                
                self.edge(path[j+1], path[j], reverseFlow)

        return maxFlow


def main():
    numInstance = int(input())
    resList = []
    for i in range(numInstance):
        numNode, numEdge = input().strip().split()
        numEdge = int(numEdge)

        g = graph()
        edgeIdx = 0
        while edgeIdx < numEdge:
            u, v, w = input().strip().split()
            g.add_edge(u, v, int(w))
            edgeIdx += 1
        
        try:
            resList.append(g.FordFulkerson("1", numNode))
        except ValueError:
            print("Oops!  That was a value error.  Try again...")
        
        
    for res in resList:
        print(res)
    
if __name__ == "__main__":
    main()


