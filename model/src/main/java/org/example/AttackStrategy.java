package org.example;

public interface AttackStrategy
{
    void attack(Creature attacker, Creature defender);
    void counterAttack(Creature creature, int damage);
//    void resetCounter();
}
