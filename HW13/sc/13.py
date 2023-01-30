from typing import List, Tuple

def max_3_sat(n: int, m: int, clauses: List[Tuple[int, int, int]]) -> List[int]:
  # Initialize assignment to all zeros
  assignment = [0] * n

  # Iterate over clauses, attempting to satisfy each one
  for x, y, z in clauses:
    # Check if any of the literals are already satisfied
    if (assignment[abs(x)-1] == 1 and x > 0) or (assignment[abs(x)-1] == 0 and x < 0):
      continue
    elif (assignment[abs(y)-1] == 1 and y > 0) or (assignment[abs(y)-1] == 0 and y < 0):
      continue
    elif (assignment[abs(z)-1] == 1 and z > 0) or (assignment[abs(z)-1] == 0 and z < 0):
      continue

    # If none of the literals are satisfied, choose one to satisfy and flip its value
    assignment[abs(x)-1] = 1 if x > 0 else 0
    assignment[abs(y)-1] = 1 if y > 0 else 0
    assignment[abs(z)-1] = 1 if z > 0 else 0

  # Return the final assignment
  return assignment
