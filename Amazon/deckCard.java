import java.io.*;
import java.util.*;

// 建立一副扑克牌
// 实现所有花色和面值的排列组合
// 实现shuffle这个组合
// 时间复杂度/空间复杂度，有没有方式降低空间复杂度，实现

enum SUIT {
  SPADE,
  DIAMOND,
  CLUB,
  HEART
}
class Card {
  SUIT suit;
  int value;
  
  public Card(SUIT suit, int value) {
    this.suit = suit;
    this.value = value;
  }
}
class DeckCard {
  Card[] cards;
  public DeckCard() {
    cards = new Card[52];
    int index = 0;
    for(SUIT suit : SUIT.values()) {
      for (int i = 0; i < 13; i++) {
        cards[index*13+i] = new Card(suit, i+1);
      }
      index++;
    }
  }
  public void shuffle() {
    Random rand = new Random();
    for (int i = 0; i < cards.length; i++) {
      int k = rand.nextInt(i+1);
      Card temp = cards[i];
      cards[i] = cards[k];
      cards[k] = temp;
    }
  }
  public void print() {
    for(Card card : cards) {
      System.out.print(card.suit+"-"+card.value+" ");
    }
  }
}
class Solution {
  public static void main(String[] args) {
    DeckCard dc = new DeckCard();
    dc.print();
    dc.shuffle();
    System.out.println("After shuffle: ");
    dc.print();
  }
}