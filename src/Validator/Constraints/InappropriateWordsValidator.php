<?php

namespace App\Validator\Constraints;

use Symfony\Component\Validator\Constraint;
use Symfony\Component\Validator\ConstraintValidator;

class InappropriateWordsValidator extends ConstraintValidator
{
    private $inappropriateWords = array(
        'sexiste',
        'test',
        'salem'
    );

    public function validate($value, Constraint $constraint)
    {
        $inappropriateWords = array();

        foreach ($this->inappropriateWords as $word) {
            if (stripos($value, $word) !== false) {
                $inappropriateWords[] = $word;
            }
        }

        if (!empty($inappropriateWords)) {
            $this->context->buildViolation($constraint->message)
                ->setParameter('{{ words }}', implode(', ', $inappropriateWords))
                ->addViolation();
        }
    }
}
