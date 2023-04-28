<?php

namespace App\Form;

use App\Entity\Message;
use App\Entity\Reclamation;
use App\Entity\Admin;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Validator\Constraints as Assert;



class MessageType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('contenu')
            ->add('date_message', DateTimeType::class, [
                'label' => 'Sélectionner la date',
                'widget' => 'single_text',
                'html5' => false,
                'format' => 'yyyy/MM/dd HH:mm',
                'input' => 'datetime_immutable',
            ])
            
            ->add('id_admin', EntityType::class, [
                'class' => Admin::class,
                'choice_label' => 'emailAd',
                'label' => 'Admin',
                'attr' => [
                    'class' => 'form-control'
                ]
            ]);
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Message::class,
        ]);
    }
}
