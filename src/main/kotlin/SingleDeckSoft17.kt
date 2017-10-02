class SingleDeckSoft17( money: Int): Player("SingleDeckSoft17", money) {
    override fun turn(dealerShows: Shoe.Card, shoe: Shoe) {
        for (hand in hands) {
            if (hand.canSplit()) {
                splitDoubleAfterSplitNotAllowedRules(dealerShows, hand, shoe)
            } else if (hand.isSoft()) {
                softRules(dealerShows, hand, shoe)
            } else {
                strategy(dealerShows, hand, shoe)
            }
        }
    }
    private fun hit(dealerShows: Shoe.Card, hand: Hand, shoe: Shoe): Boolean {
        hand.draw(shoe.deal())
        return strategy(dealerShows, hand, shoe)
    }
    private fun stand() = false
    private fun double(hand: Hand, shoe: Shoe): Boolean {
        return hand.draw(shoe.deal())
    }
    private fun strategy(dealerShows: Shoe.Card, hand: Hand, shoe: Shoe):Boolean {
        return when (hand.value()) {
            in 5..7 -> {
                hit(dealerShows, hand, shoe)
            } 8 -> when(dealerShows.rank) {
                in Shoe.Card.Rank.FIVE..Shoe.Card.Rank.SIX -> double(hand, shoe)
                else -> {
                    hit(dealerShows, hand, shoe)
                }
            } 9 -> when(dealerShows.rank) {
                in Shoe.Card.Rank.DEUCE..Shoe.Card.Rank.SIX -> double(hand, shoe)
                else -> {
                    hit(dealerShows, hand, shoe)
                }
            } 10 -> when(dealerShows.rank) {
                in Shoe.Card.Rank.DEUCE..Shoe.Card.Rank.NINE -> double(hand, shoe)
                else -> {
                    hit(dealerShows, hand, shoe)
                }
            } 11 -> double(hand, shoe)
            12 -> when(dealerShows.rank) {
                in Shoe.Card.Rank.FOUR..Shoe.Card.Rank.SIX -> {
                    stand()
                } else ->  {
                    hit(dealerShows, hand, shoe)
                }
            }
            in 13..16 -> when(dealerShows.rank) {
                in Shoe.Card.Rank.DEUCE..Shoe.Card.Rank.SIX -> {
                    stand()
                } else -> {
                    hit(dealerShows, hand, shoe)
                }
            } else -> stand()
        }
    }
    private fun splitDoubleAfterSplitNotAllowedRules(dealerShows: Shoe.Card, hand: Hand, shoe: Shoe) {
        when (hand.cards.get(0).rank) {
            Shoe.Card.Rank.DEUCE -> if (dealerShows.rank in Shoe.Card.Rank.THREE..Shoe.Card.Rank.SEVEN) {
                split(hand, shoe)
            } else {
                hit(dealerShows, hand, shoe)
            }
            Shoe.Card.Rank.THREE -> if (dealerShows.rank in Shoe.Card.Rank.FOUR..Shoe.Card.Rank.SEVEN) {
                split(hand, shoe)
            } else {
                hit(dealerShows, hand, shoe)
            }
            Shoe.Card.Rank.SIX -> if (dealerShows.rank in Shoe.Card.Rank.DEUCE..Shoe.Card.Rank.SIX) {
                split(hand, shoe)
            } else {
                hit(dealerShows, hand, shoe)
            }
            Shoe.Card.Rank.SEVEN -> if (dealerShows.rank in Shoe.Card.Rank.DEUCE..Shoe.Card.Rank.SEVEN) {
                split(hand, shoe)
            } else if (dealerShows.rank in Shoe.Card.Rank.TEN.. Shoe.Card.Rank.KING) {
                stand()
            } else {
                hit(dealerShows, hand, shoe)
            }
            Shoe.Card.Rank.EIGHT -> split(hand, shoe)
            Shoe.Card.Rank.NINE -> if ((dealerShows.rank in Shoe.Card.Rank.DEUCE..Shoe.Card.Rank.SIX) || (dealerShows.rank in Shoe.Card.Rank.EIGHT..Shoe.Card.Rank.NINE)) {
                split(hand, shoe)
            } else {
                stand()
            }
            in Shoe.Card.Rank.TEN..Shoe.Card.Rank.KING -> stand()
            Shoe.Card.Rank.ACE -> split(hand, shoe)
        }
    }
    private fun softRules(dealerShows: Shoe.Card, hand: Hand, shoe: Shoe) {

    }
}