#xrange is not defined in python3, use range instead
def insertion_sort(array):
    N = range(1, len(array))
    # Loop from the second element of the array until
    # the last element
    for i in N:
        # This is the element we want to position in its
        # correct place
        key_item = array[i]

        # Initialize the variable that will be used to
        # find the correct position of the element referenced
        # by `key_item`
        n = i - 1
        j = n

        # Run through the list of items (the left
        # portion of the array) and find the correct position
        # of the element referenced by `key_item`. Do this only
        # if `key_item` is smaller than its adjacent values.
        while j >= 0:
            # Shift the value one position to the left
            # and reposition j to point to the next element
            # (from right to left)
            if array[j] > key_item:
                array[j + 1] = array[j]
            elif array[j] == key_item:
                break
            else:
                break
            j -= 1
        # When you finish shifting the elements, you can position
        # `key_item` in its correct location
        array[j + 1] = key_item

    return array

print(insertion_sort([5, 4, 3, 3, 1]))