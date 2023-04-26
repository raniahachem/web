<?php

namespace App\Repository;

use App\Entity\Reclamation;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Reclamation>
 *
 * @method Reclamation|null find($id, $lockMode = null, $lockVersion = null)
 * @method Reclamation|null findOneBy(array $criteria, array $orderBy = null)
 * @method Reclamation[]    findAll()
 * @method Reclamation[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ReclamationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Reclamation::class);
    }
    public function findunansweredRec()
{
    $entityManager = $this->getEntityManager();
    $query = $entityManager->createQuery(
        'SELECT r
        FROM App\Entity\Reclamation r
        LEFT JOIN r.messages m
        WHERE m.id IS NULL'
    );

    return $query->getResult();
}

    
    public function findNewReclamation()
    {
        $entityManager = $this->getEntityManager();
    
        $query = $entityManager->createQuery(
            'SELECT COUNT(r)
            FROM App\Entity\Reclamation r
            WHERE r.type = :type'
        )->setParameter('type', 'nouveau');
    
        return $query->getSingleScalarResult();
    }
    

    /*public function findReclamationsByType($type)
{
    return $this->createQueryBuilder('r')
        ->select('count(r)')
        ->andWhere('r.type = :type')
        ->setParameter('type', $type)
        ->getQuery()
        ->getSingleScalarResult();
}*/

public function findReclamationsByType($type)
{
    $entityManager = $this->getEntityManager();
    $query = $entityManager->createQuery(
        'SELECT COUNT(r)
        FROM App\Entity\Reclamation r
        WHERE r.type = :type'
    )->setParameter('type', $type);

    return $query->getSingleScalarResult();
}



    
    public function save(Reclamation $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Reclamation $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }



    /*public function findReclamationsByType($type)
{
    return $this->createQueryBuilder('r')
        ->select('count(r)')
        ->andWhere('r.type = :type')
        ->setParameter('type', $type)
        ->getQuery()
        ->getSingleScalarResult();
}*/
   
//    /**
//     * @return Reclamation[] Returns an array of Reclamation objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('r.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Reclamation
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
public function findbytype($type)
{
    return $this->createQueryBuilder('reclamation')
        ->where('reclamation.type LIKE  :type')
        ->setParameter('type', '%'.$type. '%')
        ->getQuery()
        ->getResult();
}

public function orderByType()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.type', 'ASC')
            ->getQuery()->getResult();
    }
    public function order_By_Date()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.date_r', 'ASC')
            ->getQuery()->getResult();
    }
    public function trierpardate()
    {
        return $this->createQueryBuilder('r')
            ->orderBy('r.date_r', 'DESC')
            ->getQuery()
            ->getResult();
    }
    

}
