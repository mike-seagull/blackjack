import java.util.*

class Hand {
    var cards = LinkedList<Shoe.Card>()

    fun draw(c : Shoe.Card) = cards.add(c)

    /*
    private fun calculateAceValue(numberOfAcesValuedAt11: Int, numberOfAcesValuedAt1: Int, handValue: Int): Int{
        return if (numberOfAcesValuedAt11 == 0) {
            (numberOfAcesValuedAt1 * 1) + handValue
        } else if (((numberOfAcesValuedAt11 * 11) + (numberOfAcesValuedAt1 * 1) + handValue) > 21) {
            calculateAceValue((numberOfAcesValuedAt11 - 1), (numberOfAcesValuedAt1 + 1), handValue)
        } else {
            (numberOfAcesValuedAt11 * 11) + (numberOfAcesValuedAt1 * 1) + handValue
        }
    }
    */
    private var isSoft = false

    fun isSoft(): Boolean {
        value()
        return isSoft
    }
    fun value(): Int {
        isSoft = false
        sort()
        var handValue = 0
        var ace_hand = LinkedList<Shoe.Card>()
        for(card in cards) {
            when (card.rank) {
                in Shoe.Card.Rank.DEUCE..Shoe.Card.Rank.NINE ->      handValue += card.rank.value
                in Shoe.Card.Rank.TEN..Shoe.Card.Rank.KING ->    handValue += 10
                Shoe.Card.Rank.ACE -> ace_hand.add(card)
            }
        }

        // calculateAceValue
        var stack = Stack<Int>()
        stack.push(0) // numberOfAcesValuedAt1
        stack.push(ace_hand.size) // numberOfAcesValuedAt11
        while ( !stack.isEmpty() ) {
            var numberOfAcesValuedAt11 = stack.pop()
            var numberOfAcesValuedAt1 = stack.pop()
            if (numberOfAcesValuedAt11 == 0) {
                handValue += (numberOfAcesValuedAt1 * 1)
            } else if (((numberOfAcesValuedAt11 * 11) + (numberOfAcesValuedAt1 * 1) + handValue) > 21) {
                //calculateAceValue((numberOfAcesValuedAt11 - 1), (numberOfAcesValuedAt1 + 1), handValue)
                stack.push(numberOfAcesValuedAt1 + 1)
                stack.push(numberOfAcesValuedAt11 - 1)
            } else {
                isSoft = true
                handValue += (numberOfAcesValuedAt11 * 11) + (numberOfAcesValuedAt1 * 1)
            }
        }
        return handValue
    }
    fun isBlackJack(): Boolean = value() == 21
    fun isNaturalBlackJack(): Boolean = isBlackJack() && cards.size == 2
    fun isBust(): Boolean = value() > 21
    fun canSplit(): Boolean = cards.size == 2 && cards.get(0).rank == cards.get(1).rank
    fun clear() = cards.clear()
    fun shows():Shoe.Card = cards.first


    private fun sort() {
        //if (cards.size > 1) {
        // least to greatest
        cards.sortBy { card ->
            card.rank.value
        }
        // greatest to least
        Collections.reverse(cards)
        //}
    }
}