<?php

namespace App\Form;

use App\Entity\Reclamation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints\ValidCaptcha;
use Gregwar\CaptchaBundle\Type\CaptchaType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

class ReclamationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {   

        $options = array(
            'technique' => 'technique',
            'service' => 'service',
            'autre ' => 'autre',
        );
        $builder
        ->add('type', ChoiceType::class, array(
            'choices' => $options,
        ))
            ->add('date_r')
            ->add('description')
            ->add('captcha', CaptchaType::class,[
                'attr' => [
                   
                    'class' => "form-control"
                ],
                ]
            );
            //->add('id_client')
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
