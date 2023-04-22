<?php

namespace App\Form;

use App\Entity\Abonnement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

class AbonnementsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('typeab', null, [
                'label' => 'Type d\'abonnement',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;'
                ]
            ])
            ->add('prixab', null, [
                'label' => 'Prix de l\'abonnement',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;'
                ]
            ])
            ->add('modepaiementab', null, [
                'label' => 'Mode de paiement',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;'
                ]
            ])
            ->add('save', SubmitType::class, [
                'label' => 'Enregistrer',
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
            'data_class' => Abonnement::class,
        ]);
    }
}
