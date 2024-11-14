package org.example;

public class DoubleCounterStrategy implements AttackStrategy
{

    private int counterCount = 0;

    @Override
    public void attack(Creature attacker, Creature defender)
    {
        int damage = attacker.getDmgCalc().calculateDamage(attacker, defender);
        defender.applyDamage(damage);
    }

    @Override
    public void counterAttack(Creature creature, int dmg)
    {
        if (counterCount < 2)
        {
            creature.applyDamage(dmg);
            counterCount++;
        }
    }

    public void resetCounter()
    {
        counterCount = 0;
    }
}
