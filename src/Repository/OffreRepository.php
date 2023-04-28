<?php

namespace App\Repository;

use Symfony\Component\HttpFoundation\RequestStack;
use App\Entity\Offre;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

use Symfony\Component\Mime\Email;
 
use Symfony\Component\Mailer\MailerInterface;
use App\Service\MailerService;
use Doctrine\Common\Collections\Criteria;

/**
 * @extends ServiceEntityRepository<Offre>
 *
 * @method Offre|null find($id, $lockMode = null, $lockVersion = null)
 * @method Offre|null findOneBy(array $criteria, array $orderBy = null)
 * @method Offre[]    findAll()
 * @method Offre[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class OffreRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry, RequestStack $requestStack)
    {
        parent::__construct($registry, Offre::class);
        $this->requestStack = $requestStack;
    }

    public function save(Offre $entity,MailerInterface $mailer, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);


        $email = (new Email())
        ->from('nour.benmiled@esprit.tn')
        ->to('ons.hamdi@esprit.tn')
        ->subject('Notification')
        ->html('<p> Votre offre a été publié avec succès '. $entity->getIdOffre().'</p>');

    $mailer->send($email);
    
    $session = $this->requestStack->getCurrentRequest()->getSession();
    $session->getFlashBag()->add(
        'success',
        '  avec succès'
       
    );


        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Offre $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

 public function removeByRe(int $id, bool $flush = false): void
{
    $offre = $this->getEntityManager()->getRepository(Offre::class)->find($id);

    if ($offre !== null) {
        $this->getEntityManager()->remove($offre);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }
}
/**
 * @return Offre|null Returns an Offre object or null if not found
 */
public function findById(int $id): ?Offre
{
    return $this->createQueryBuilder('o')
        ->andWhere('o.idOffre = :id')
        ->setParameter('id', $id)
        ->getQuery()
        ->getOneOrNullResult();
}

public function findByIdConducteur(int $id): ?Offre
{
    return $this->createQueryBuilder('o')
        ->andWhere('o.idConducteur = :id')
        ->setParameter('id', $id)
        ->getQuery()
        ->getOneOrNullResult();
}

/**
 * @return Offre|null Returns an Offre object or null if not found
 */
public function findByvehicule(int $typeVehicule): ?Offre
{
    return $this->createQueryBuilder('o')
        ->andWhere('o.typeVehicule = :typeVehicule')
        ->setParameter('typeVehicule', $typeVehicule)
        ->getQuery()
        ->getOneOrNullResult();
}
 
public function findLikeNom(?string $searchTerm)
{  
    return $this->createQueryBuilder('o')
    ->select('o.idOffre,o.typeVehicule,o.idConducteur,o.destination,o.ptDepart,o.prix ')
        ->orwhere('o.idOffre LIKE :searchTerm')
        ->orwhere('o.idConducteur LIKE :searchTerm')
        ->orwhere('o.destination LIKE :searchTerm')
        ->orwhere('o.ptDepart LIKE :searchTerm')
        ->orwhere('o.prix LIKE :searchTerm')
        ->orwhere('o.typeVehicule LIKE :searchTerm')
        ->setParameter('searchTerm', '%'.$searchTerm.'%')
        ->getQuery()
        ->getResult();
}
 
public function findbytype($typeVehicule)
{
    return $this->createQueryBuilder('offre')
        ->where('offre.typeVehicule LIKE  :typeVehicule')
        ->setParameter('typeVehicule', '%'.$typeVehicule. '%')
        ->getQuery()
        ->getResult();
}

/* private function createApprovedCriteria(): Criteria
    {
        return Criteria::create()
            ->andWhere(Criteria::expr()->eq('approved', true));
    } */
/*
/**
 * @return Offre[]
 */
/*
public function findMostPopular(String $search = null): array{
    $queryBuilder =  $this->createQueryBuilder('offre')
    ->addCriteria(self::createApprovedCriteria())
    ->orderBy('offre.idOffre','DESC');
 
    if($search) {
        $queryBuilder->andWhere('offre.content LIKE :searchTerm')
        ->setParameter('searchTerm','%'.$search.'%');

    }
    return $queryBuilder
    ->setMaxResults(7)
    ->getQuery()
    ->getResult();
}*/
 
 
/*public function findByNom($searchTerm)
{
    return $this->createQueryBuilder('o')
        ->where('o.idOffre LIKE :searchTerm')
        ->setParameter('searchTerm', '%'.$searchTerm.'%')
        ->getQuery()
        ->getResult();
}*/
 








//    /**
//     * @return Offre[] Returns an array of Offre objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('o')
//            ->andWhere('o.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('o.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Offre
//    {
//        return $this->createQueryBuilder('o')
//            ->andWhere('o.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
