<?php

namespace App\Form;

use App\Entity\Payement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

class PaymentType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('clientid', null, [
                'label' => 'Client ID',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;'
                ]
            ])
            ->add('modepayment', null, [
                'label' => 'Mode of Payment',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;'
                ]
            ])
            ->add('prixcourse', null, [
                'label' => 'Prix Course',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;'
                ]
            ])
            ->add('paymentdate', null, [
                'label' => 'Payment Date',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;'
                ]
            ])
            ->add('save',SubmitType::class, [
                'label' => 'Save',
                'attr' => [
                    'class' => 'btn btn-primary',
                    'style' => 'border-radius: 10px;'
                ]
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Payement::class,
        ]);
    }
}
