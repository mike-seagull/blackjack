import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestHand {
    private var hand = Hand()

    @Before fun setup() {
        hand.clear()
    }

    @Test fun testDraw() {
        var s = Shoe()
        hand.draw(s.deal())
        Assert.assertEquals(1, hand.cards.size)
        hand.draw(s.deal())
        Assert.assertEquals(2, hand.cards.size)
    }
    @Test fun testValue() {
        // test 2 - 9
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card1Rank in Shoe.Card.Rank.DEUCE.value..Shoe.Card.Rank.NINE.value) {
                for (card2Suit in Shoe.Card.Suit.values()) {
                    for (card2Rank in Shoe.Card.Rank.DEUCE.value..Shoe.Card.Rank.NINE.value) {
                        var card1 = Shoe.Card(Shoe.Card.Rank.from(card1Rank), card1Suit)
                        hand.draw(card1)
                        var card2 = Shoe.Card(Shoe.Card.Rank.from(card2Rank), card2Suit)
                        hand.draw(card2)
                        if (card1Rank+card2Rank != hand.value()) {
                            Assert.fail("\n$card1 \n and \n $card2 \n do not equal ${hand.value()}")
                        } else {
                            Assert.assertEquals(card1Rank + card2Rank, hand.value())
                        }
                        hand.cards.clear()
                    }
                }
            }
        }

        // test face cards
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card1Rank in Shoe.Card.Rank.TEN.value..Shoe.Card.Rank.KING.value) {
                for (card2Suit in Shoe.Card.Suit.values()) {
                    for (card2Rank in Shoe.Card.Rank.DEUCE.value..Shoe.Card.Rank.NINE.value) {
                        var card1 = Shoe.Card(Shoe.Card.Rank.from(card1Rank), card1Suit)
                        hand.draw(card1)
                        var card2 = Shoe.Card(Shoe.Card.Rank.from(card2Rank), card2Suit)
                        hand.draw(card2)
                        if (10+card2Rank != hand.value()) {
                            Assert.fail("\n$card1 \n and \n $card2 \n do not equal ${hand.value()}")
                        } else {
                            Assert.assertEquals(10 + card2Rank, hand.value())
                        }
                        hand.cards.clear()
                    }
                }
            }
        }
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card1Rank in Shoe.Card.Rank.TEN.value..Shoe.Card.Rank.KING.value) {
                for (card2Suit in Shoe.Card.Suit.values()) {
                    for (card2Rank in Shoe.Card.Rank.TEN.value..Shoe.Card.Rank.TEN.value) {
                        var card1 = Shoe.Card(Shoe.Card.Rank.from(card1Rank), card1Suit)
                        hand.draw(card1)
                        var card2 = Shoe.Card(Shoe.Card.Rank.from(card2Rank), card2Suit)
                        hand.draw(card2)
                        if ( hand.value() != 20) {
                                Assert.fail("\n$card1 \n and \n $card2 \n do not equal ${hand.value()}")
                            } else {
                                Assert.assertEquals(20, hand.value())
                        }
                        hand.cards.clear()
                    }
                }
            }
        }

        //test aces
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card2Suit in Shoe.Card.Suit.values()) {
                for (card2Rank in Shoe.Card.Rank.DEUCE.value..Shoe.Card.Rank.NINE.value) {
                    var card1 = Shoe.Card(Shoe.Card.Rank.ACE, card1Suit)
                    hand.draw(card1)
                    var card2 = Shoe.Card(Shoe.Card.Rank.from(card2Rank), card2Suit)
                    hand.draw(card2)
                    if (11+card2Rank != hand.value()) {
                        Assert.fail("\n$card1 \n and \n $card2 \n do not equal ${hand.value()}")
                    } else {
                        Assert.assertEquals(11 + card2Rank, hand.value())
                    }
                    hand.cards.clear()
                }
            }
        }
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card2Suit in Shoe.Card.Suit.values()) {
                for (card2Rank in Shoe.Card.Rank.TEN.value..Shoe.Card.Rank.TEN.value) {
                    var card1 = Shoe.Card(Shoe.Card.Rank.ACE, card1Suit)
                    hand.draw(card1)
                    var card2 = Shoe.Card(Shoe.Card.Rank.from(card2Rank), card2Suit)
                    hand.draw(card2)
                    if (hand.value() != 21) {
                        Assert.fail("\n$card1 \n and \n $card2 \n do not equal ${hand.value()}")
                    } else {
                        Assert.assertEquals(21, hand.value())
                    }
                    hand.cards.clear()
                }
            }
        }
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card2Suit in Shoe.Card.Suit.values()) {
                for (card2Rank in Shoe.Card.Rank.TEN.value..Shoe.Card.Rank.TEN.value) {
                    var card1 = Shoe.Card(Shoe.Card.Rank.ACE, card1Suit)
                    hand.draw(card1)
                    var card2 = Shoe.Card(Shoe.Card.Rank.from(card2Rank), card2Suit)
                    hand.draw(card2)
                    if (hand.value() != 21) {
                        Assert.fail("\n$card1 \n and \n $card2 \n do not equal ${hand.value()}")
                    } else {
                        Assert.assertEquals(21, hand.value())
                    }
                    hand.cards.clear()
                }
            }
        }
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card2Suit in Shoe.Card.Suit.values()) {
                var card1 = Shoe.Card(Shoe.Card.Rank.ACE, card1Suit)
                hand.draw(card1)
                var card2 = Shoe.Card(Shoe.Card.Rank.ACE, card2Suit)
                hand.draw(card2)
                if (hand.value() != 12) {
                    Assert.fail("\n$card1 \n and \n $card2 \n do not equal ${hand.value()}")
                } else {
                    Assert.assertEquals(12, hand.value())
                }
                hand.cards.clear()
            }
        }
    }
    @Test fun testBust() {
        hand.draw( Shoe.Card(Shoe.Card.Rank.KING, Shoe.Card.Suit.DIAMONDS) )
        hand.draw( Shoe.Card(Shoe.Card.Rank.SEVEN, Shoe.Card.Suit.DIAMONDS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.SEVEN, Shoe.Card.Suit.HEARTS) )
        Assert.assertTrue(hand.isBust())

        hand.clear()

        hand.draw( Shoe.Card(Shoe.Card.Rank.KING, Shoe.Card.Suit.DIAMONDS) )
        hand.draw( Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.DIAMONDS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.SIX, Shoe.Card.Suit.HEARTS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.DEUCE, Shoe.Card.Suit.HEARTS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.DEUCE, Shoe.Card.Suit.CLUBS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.DEUCE, Shoe.Card.Suit.SPADES) )
        Assert.assertTrue(hand.isBust())

        hand.clear()

        hand.draw( Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.SPADES) )
        hand.draw( Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.HEARTS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.CLUBS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.DIAMONDS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.FIVE, Shoe.Card.Suit.HEARTS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.DEUCE, Shoe.Card.Suit.HEARTS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.DEUCE, Shoe.Card.Suit.CLUBS) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.DEUCE, Shoe.Card.Suit.SPADES) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.SIX, Shoe.Card.Suit.SPADES) )
        Assert.assertFalse(hand.isBust())
        hand.draw( Shoe.Card(Shoe.Card.Rank.FOUR, Shoe.Card.Suit.SPADES) )
        Assert.assertTrue(hand.isBust())
    }
    @Test fun testBlackJack() {
        hand.draw( Shoe.Card(Shoe.Card.Rank.KING, Shoe.Card.Suit.DIAMONDS) )
        hand.draw( Shoe.Card(Shoe.Card.Rank.ACE, Shoe.Card.Suit.DIAMONDS) )
        Assert.assertTrue(hand.isBlackJack())
        hand.draw( Shoe.Card(Shoe.Card.Rank.DEUCE, Shoe.Card.Suit.HEARTS) )
        Assert.assertFalse(hand.isBlackJack())
        hand.draw( Shoe.Card(Shoe.Card.Rank.THREE, Shoe.Card.Suit.HEARTS) )
        Assert.assertFalse(hand.isBlackJack())
        hand.draw( Shoe.Card(Shoe.Card.Rank.FIVE, Shoe.Card.Suit.HEARTS) )
        Assert.assertTrue(hand.isBlackJack())
    }
    @Test fun testCanSplit() {
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card2Suit in Shoe.Card.Suit.values()) {
                for (cardRank in Shoe.Card.Rank.values()) {
                    var card1 = Shoe.Card(cardRank, card1Suit)
                    hand.draw(card1)
                    var card2 = Shoe.Card(cardRank, card2Suit)
                    hand.draw(card2)
                    Assert.assertTrue(hand.canSplit())
                    hand.cards.clear()
                }
            }
        }
    }

    @Test fun testIsSoft() {
        // all 2-card hands that do not have an ace, are hard
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card1Rank in Shoe.Card.Rank.DEUCE.value..Shoe.Card.Rank.KING.value) {
                for (card2Suit in Shoe.Card.Suit.values()) {
                    for (card2Rank in Shoe.Card.Rank.DEUCE.value..Shoe.Card.Rank.KING.value) {
                        var card1 = Shoe.Card(Shoe.Card.Rank.from(card1Rank), card1Suit)
                        hand.draw(card1)
                        var card2 = Shoe.Card(Shoe.Card.Rank.from(card2Rank), card2Suit)
                        hand.draw(card2)
                        if (hand.isSoft()) {
                            Assert.fail("\n$card1 \nand \n$card2 \nis not soft")
                        } else {
                            Assert.assertFalse(hand.isSoft())
                        }
                        hand.clear()
                    }
                }
            }
        }
        // all 2-card hands with at least one ace, are soft
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card2Suit in Shoe.Card.Suit.values()) {
                for (card2Rank in Shoe.Card.Rank.values()) {
                    var card1 = Shoe.Card(Shoe.Card.Rank.ACE, card1Suit)
                    hand.draw(card1)
                    var card2 = Shoe.Card(card2Rank, card2Suit)
                    hand.draw(card2)
                    if (!hand.isSoft()) {
                        Assert.fail("\n$card1 \nand \n$card2 \nis soft")
                    } else {
                        Assert.assertTrue(hand.isSoft())
                    }
                    hand.clear()
                }
            }
        }
        // only soft hand should be 2 aces and a 10 card
        for (card1Suit in Shoe.Card.Suit.values()) {
            for (card2Suit in Shoe.Card.Suit.values()) {
                for (card3Suit in Shoe.Card.Suit.values()) {
                    for (card3Rank in Shoe.Card.Rank.values()) {
                        var ace1 = Shoe.Card(Shoe.Card.Rank.ACE, card1Suit)
                        var ace2 = Shoe.Card(Shoe.Card.Rank.ACE, card2Suit)
                        var card3 = Shoe.Card(card3Rank, card3Suit)
                        hand.draw(ace1)
                        hand.draw(ace2)
                        hand.draw(card3)
                        when {
                            card3Rank in Shoe.Card.Rank.TEN..Shoe.Card.Rank.KING
                                    && hand.isSoft() -> Assert.fail("$card3 makes the hand soft")
                            card3Rank in Shoe.Card.Rank.TEN..Shoe.Card.Rank.KING
                                    && !hand.isSoft() -> Assert.assertFalse(hand.isSoft())
                            card3Rank !in Shoe.Card.Rank.TEN..Shoe.Card.Rank.KING
                                    && !hand.isSoft() -> Assert.fail("$card3 makes the hand not soft")
                            else -> Assert.assertTrue(hand.isSoft())
                        }
                        hand.clear()
                    }
                }
            }
        }
    }
}