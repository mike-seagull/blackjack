import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestDealer {
    private var dealer = Dealer()
    private var shoe = Shoe()

    @Before fun setup() {
        dealer.hand.clear()
        shoe = Shoe()
        shoe.shuffle()
    }
    @Test fun testStandOnNaturalBlackJack() {
        dealer.hand.draw(Shoe.Card(Shoe.Card.Rank.KING, Shoe.Card.Suit.DIAMONDS))
        dealer.hand.draw(Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.DIAMONDS))
        dealer.turn(shoe)
        Assert.assertTrue(dealer.hand.isNaturalBlackJack())
    }

    @Test fun testHitSoft17() {
        dealer.hand.draw(Shoe.Card(Shoe.Card.Rank.SIX, Shoe.Card.Suit.DIAMONDS))
        dealer.hand.draw(Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.DIAMONDS))
        dealer.turn(shoe)
        Assert.assertTrue(dealer.hand.cards.size > 2)
        Assert.assertFalse(dealer.hand.isSoft() && dealer.hand.value() == 17)
        Assert.assertTrue(shoe.cards.size < 52)
    }
    @Test fun testStandSoft18AndAbove() {
        for (rank in Shoe.Card.Rank.SEVEN.value..Shoe.Card.Rank.NINE.value) {
            dealer.hand.draw(Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.DIAMONDS))
            var cardRank = Shoe.Card.Rank.from(rank)
            dealer.hand.draw(Shoe.Card(cardRank, Shoe.Card.Suit.DIAMONDS))
            var handValueBeforeTurn = dealer.hand.value()
            dealer.turn(shoe)
            Assert.assertTrue(dealer.hand.cards.size == 2)
            Assert.assertEquals(handValueBeforeTurn, dealer.hand.value())
            dealer.hand.clear()
        }

    }
    @Test fun testHitLessThan17() {
        for (rank in Shoe.Card.Rank.DEUCE.value..Shoe.Card.Rank.SIX.value) {
            dealer.hand.draw(Shoe.Card(Shoe.Card.Rank.KING, Shoe.Card.Suit.DIAMONDS))
            var cardRank = Shoe.Card.Rank.from(rank)
            dealer.hand.draw(Shoe.Card(cardRank, Shoe.Card.Suit.DIAMONDS))
            dealer.turn(shoe)
            Assert.assertTrue(dealer.hand.cards.size > 2)
            dealer.hand.clear()
        }
        Assert.assertTrue(shoe.cards.size < 52)
    }
}