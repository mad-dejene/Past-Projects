VOWELS = 'aeiou'

def convert_word(word):
    m = 0
    first_letter = word[0]
    letters_getting_moved = ""
    new_word = ""
    if first_letter in VOWELS:
        return word + "hay"
    else:
        while word[m] not in VOWELS:
            letters_getting_moved = letters_getting_moved + word[m]
            m = m+1
        new_word = new_word + word [m:] + letters_getting_moved + "ay"
        return new_word
        

def convert_sentence(sentence):
    list_of_words = sentence.split(' ')
    new_sentence = ""
    for word in list_of_words:
        new_sentence = new_sentence + convert_word(word)
        new_sentence = new_sentence + " "
    return new_sentence

print "Type in a sentence, and it'll get converted to Pig-Latin!"
print "Please don't use punctuation or numbers."
print "Also, we can't handle uppercase/lowercase yet, so lower only please!"

text = raw_input()

print convert_sentence(text)
