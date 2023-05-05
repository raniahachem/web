<?php

namespace App\Validator;

use Symfony\Component\Validator\Constraint;
use Symfony\Component\Validator\ConstraintValidator;

class ImmatriculationValidator extends ConstraintValidator
{
    public function validate($value, Constraint $constraint)
    {
        if (null === $value || '' === $value) {
            return;
        }

        preg_match_all('/\d+/', $value, $matches);
        $numbers = $matches[0];

        if ((int)$numbers[0] < 1 || (int)$numbers[0] > 999) {
            $this->context->buildViolation($constraint->message)
                ->setParameter('{{ value }}', $value)
                ->addViolation();
        }

        if ((int)$numbers[1] < 1 || (int)$numbers[1] > 9999) {
            $this->context->buildViolation($constraint->message)
                ->setParameter('{{ value }}', $value)
                ->addViolation();
        }
    }
}
