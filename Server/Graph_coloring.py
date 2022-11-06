def backtracking(graph):
    '''
    Returns the an array with i elements, where the ith element is the solution
    for the ith element of the passed in graph.
    The type of elements returned is driven by the get_domain_values helper
    function - in this example it is the color for each continent
    '''
    solution = [None] * len(graph)

    if backtracking_rec(graph, solution, next_var, 0):
        return solution

    return None

def backtracking_rec(graph, solution, current):
    if current == None:
        return True

    for color in get_domain_values(graph, solution, current):
        solution[current] = color

        next_var = select_unassigned(graph, solution, current)
        if backtracking_rec(graph, solution, next_var):
            return True

        solution[current] = None

    return False

def select_unassigned(_graph, solution, current):
    if current + 1 < len(solution):
        return current + 1
    return None

def get_domain_values(graph, solution, current):
    for color in range(len(graph)):
        for i, is_neighbour in enumerate(graph[current]):
            if is_neighbour == 1 and solution[i] == color:
                break
        else:
            yield color

graph = [
	[0, 1, 0, 0, 1],
	[1, 0, 1, 0, 1],
	[0, 1, 0, 1, 1],
	[0, 0, 1, 0, 1],
	[1, 1, 1, 1, 0],
]

colors = backtracking(graph)