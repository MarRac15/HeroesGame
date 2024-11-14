package org.example;

public class RangedAttackStrategy implements AttackStrategy
{
    @Override
    public void attack(Creature attacker, Creature defender)
    {
        int damage = attacker.getDmgCalc().calculateDamage(attacker, defender);
        defender.applyDamage(damage);
    }

    @Override
    public void counterAttack(Creature creature, int damage)
    {
        creature.applyDamage(damage);
    }

}
