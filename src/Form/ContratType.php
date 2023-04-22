<?php

namespace App\Form;

use App\Entity\Contrat;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ContratType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('dateDebut', null, [
                'label' => 'Date de début',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;',
                ]
            ])
            ->add('dateFin', null, [
                'label' => 'Date de fin',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;',
                ]
            ])
            ->add('prix', null, [
                'label' => 'Prix',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;',
                ]
            ])
            ->add('statut', null, [
                'label' => 'Statut',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;',
                ]
            ])
            ->add('idAdmin', null, [
                'label' => 'ID Admin',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;',
                ]
            ])
            ->add('idConducteur', null, [
                'label' => 'ID Conducteur',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;',
                ]
            ])
            ->add('qrCode', null, [
                'label' => 'QR Code',
                'attr' => [
                    'class' => 'form-control',
                    'style' => 'border-radius: 10px; margin-bottom: 10px;',
                ]
            ])
           
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Contrat::class,
        ]);
    }
}
