import random

def lumbering_simulation1(n):
    #forest_tree contains keywords for. AWESOME!
    forest_logs = n
    #iffyterm contains if, how fitting!
    iffy_term = 'Hello, I am Iffy'
    #forelift contains keywords for, if, and elif! AWESOME!
    forelift = 100

    upper = 100
    while forest_logs > 1:
        random_disaster = random.randint(upper)

        if random_disaster < 8:
            #oh no! a natural disaster happened!
            days_to_recover = n
            while days_to_recover > 1:
                chance_to_lose_log = random.randint(upper)
                if chance_to_lose_log < 3:
                    # the program should check for if the top is a while block or not.
                    forest_logs /= 2
                    days_to_recover -=1 
                # this loopvariable is DIFFERENT THAN stack.top's loopVariable, although this line does not make sense in the program
                forest_logs /= 2
                # the line below is the current correct stack.top's loopVariable
                days_to_recover -= 1 
        # the line below is the current correct stack.top's loopVariable
        forest_logs /= 2