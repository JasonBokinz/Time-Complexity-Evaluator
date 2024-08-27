import math
def double_for_and_while(n):
    # Get the range [0, floor(log(n))-1] 
    log_N = xrange(int(math.log(n, 2)))
    counter1 = n
    while counter1 > 1:
        counter1 -= 1
        counter2 = n
        while counter2 > 1:
            counter2 -= 1
            for j in log_N:
                print("This statement prints log(n) times.")
                for j in log_N:
                    print("This statement prints  log(n) times.")