import java.util.*

class Player(var name:String = "", var money:Int = 100) {
    var hands = ArrayList<Hand>()

}
class Hand {
    var cards = LinkedList<Shoe.Card>()
    var isSoft = false
        get() {
            value()
            return isSoft
        }

    fun draw(c : Shoe.Card) = cards.add(c)

    fun value(): Int {
        sort()
        var handValue = 0
        for(card in cards) {
            when (card.rank) {
                in Shoe.Card.Rank.DEUCE..Shoe.Card.Rank.NINE ->      handValue += card.rank.value
                in Shoe.Card.Rank.TEN..Shoe.Card.Rank.KING ->    handValue += 10
                Shoe.Card.Rank.ACE -> {
                    if (handValue + 11 > 21) {
                        handValue += 1
                    } else {
                        handValue += 11
                        isSoft = true
                    }
                }
            }
        }
        return handValue
    }
    fun hasBlackJack(): Boolean = value() == 21
    private fun sort() {
        // least to greatest
        cards.sortBy {
            card -> card.rank.value
        }
        // greatest to least
        //Collections.reverse(cards)
    }
}