import java.util.*

class Shoe(var numberOfDecks: Int = 1) {
    var cards = LinkedList<Card>()

    init {
        for (i in 1..numberOfDecks) {
            val d = Deck()
            cards.addAll(d.cards)
        }
    }

    fun shuffle() = Collections.shuffle(cards)
    fun deal(): Card = cards.pop()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Shoe
        if (!Arrays.equals(other.cards.toArray(), cards.toArray())) return false

        return true
    }

    private class Deck {
        var cards = LinkedList<Card>()

        init {
            for(suit in Card.Suit.values()) {
                for (rank in Card.Rank.values()) {
                    cards.add(Card(rank, suit))
                }
            }
        }
    }
    class Card(var rank: Rank, var suit: Suit) {
        enum class Suit { CLUBS, DIAMONDS, HEARTS, SPADES }
        enum class Rank(var value : Int) {
            ACE(1), DEUCE(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
            TEN(10), JACK(11), QUEEN(12), KING(13);

            companion object {
                fun from(findValue: Int): Rank {
                    when (findValue) {
                        Rank.DEUCE.value -> return Rank.DEUCE
                        Rank.THREE.value -> return Rank.THREE
                        Rank.FOUR.value -> return Rank.FOUR
                        Rank.FIVE.value -> return Rank.FIVE
                        Rank.SIX.value -> return Rank.SIX
                        Rank.SEVEN.value -> return Rank.SEVEN
                        Rank.EIGHT.value -> return Rank.EIGHT
                        Rank.NINE.value -> return Rank.NINE
                        Rank.TEN.value -> return Rank.TEN
                        Rank.JACK.value -> return Rank.JACK
                        Rank.QUEEN.value -> return Rank.QUEEN
                        Rank.KING.value -> return Rank.KING
                        else -> return Rank.ACE
                    }
                }
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other?.javaClass != javaClass) return false
            other as Card
            return (other.rank.equals(rank) && other.suit.equals(suit))
        }
        override fun toString(): String {
            return String.format("%s of %s", rank, suit)
        }
    }
}

